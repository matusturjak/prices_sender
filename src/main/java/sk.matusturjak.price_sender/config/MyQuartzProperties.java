package sk.matusturjak.price_sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quartz")
public class MyQuartzProperties {

    private String tescoPromotionsDownloadCron;
    private String sendEmailsCron;

    public String getTescoPromotionsDownloadCron() {
        return tescoPromotionsDownloadCron;
    }

    public void setTescoPromotionsDownloadCron(String tescoPromotionsDownloadCron) {
        this.tescoPromotionsDownloadCron = tescoPromotionsDownloadCron;
    }

    public String getSendEmailsCron() {
        return sendEmailsCron;
    }

    public void setSendEmailsCron(String sendEmailsCron) {
        this.sendEmailsCron = sendEmailsCron;
    }
}
