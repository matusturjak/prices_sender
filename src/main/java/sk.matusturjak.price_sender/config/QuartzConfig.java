package sk.matusturjak.price_sender.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import sk.matusturjak.price_sender.quartz.SendEmailsJob;
import sk.matusturjak.price_sender.quartz.TescoPromotionsDownloadJob;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

@Slf4j
@Configuration
public class QuartzConfig {
    private final DataSource dataSource;
    private final ApplicationContext applicationContext;
    private final QuartzProperties quartzProperties;
    private MyQuartzProperties myQuartzProperties;

    public QuartzConfig(DataSource dataSource, ApplicationContext applicationContext, QuartzProperties quartzProperties, MyQuartzProperties myQuartzProperties) {
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
        this.quartzProperties = quartzProperties;
        this.myQuartzProperties = myQuartzProperties;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    private Properties getQuartzProperties() {
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());
        log.info("Quartz properties set '{}'", properties);
        return properties;
    }

    @Bean
    public JobDetailFactoryBean tescoPromotionsDownloadJobDetail() {
        return getJobDetailFactoryBean(TescoPromotionsDownloadJob.class, "add documents from mongo to rabbitmq queue for deletion");
    }

    @Bean
    public CronTriggerFactoryBean tescoPromotionsDownloadTrigger(JobDetail tescoPromotionsDownloadJobDetail) {
        return getCronTriggerFactoryBean(tescoPromotionsDownloadJobDetail, myQuartzProperties.getTescoPromotionsDownloadCron());
    }

    @Bean
    public SchedulerFactoryBean tescoPromotionsDownloadScheduler(Trigger tescoPromotionsDownloadTrigger, JobDetail tescoPromotionsDownloadJobDetail) {
        return getSchedulerFactoryBean(tescoPromotionsDownloadTrigger, tescoPromotionsDownloadJobDetail);
    }

    @Bean
    public JobDetailFactoryBean sendEmailsJobDetail() {
        return getJobDetailFactoryBean(SendEmailsJob.class, "add documents from mongo to rabbitmq queue for deletion");
    }

    @Bean
    public CronTriggerFactoryBean sendEmailsTrigger(JobDetail sendEmailsJobDetail) {
        return getCronTriggerFactoryBean(sendEmailsJobDetail, myQuartzProperties.getSendEmailsCron());
    }

    @Bean
    public SchedulerFactoryBean sendEmailsScheduler(Trigger sendEmailsTrigger, JobDetail sendEmailsJobDetail) {
        return getSchedulerFactoryBean(sendEmailsTrigger, sendEmailsJobDetail);
    }

    private JobDetailFactoryBean getJobDetailFactoryBean(Class<? extends Job> jobClass, String description) {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(jobClass);
        jobDetailFactory.setDescription(description);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    private CronTriggerFactoryBean getCronTriggerFactoryBean(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setCronExpression(cronExpression);
        trigger.setTimeZone(TimeZone.getTimeZone(Calendar.getInstance().getTimeZone().toZoneId()));
        return trigger;
    }

    private SchedulerFactoryBean getSchedulerFactoryBean(Trigger trigger, JobDetail jobDetail) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setQuartzProperties(getQuartzProperties());
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(jobDetail);
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        return schedulerFactory;
    }

}
