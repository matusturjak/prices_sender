package sk.matusturjak.price_sender.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.service.ItemService;
import sk.matusturjak.price_sender.service.TescoParser;

import java.io.IOException;

@Slf4j
@Component
public class TescoPromotionsDownloadJob implements Job {
    @Autowired
    private TescoParser tescoParser;
    @Autowired
    private ItemService itemService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("TescoPromotionsDownloadJob:execute - START");
            tescoParser.getItemsWithClubcard();
            log.info("TescoPromotionsDownloadJob:execute - END");
        } catch (IOException e) {
            log.error("TescoPromotionsDownloadJob:execute - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
