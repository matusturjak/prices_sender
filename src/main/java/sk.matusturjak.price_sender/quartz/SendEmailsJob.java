package sk.matusturjak.price_sender.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.service.MailService;

@Slf4j
@Component
public class SendEmailsJob implements Job {

    @Autowired
    private MailService mailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("SendEmailsJob:execute - START");
        mailService.sendEmails();
        log.info("SendEmailsJob:execute - END");
    }
}
