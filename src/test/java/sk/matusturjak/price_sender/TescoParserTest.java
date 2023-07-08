package sk.matusturjak.price_sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.matusturjak.price_sender.service.TescoParser;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TescoParserTest {

    @Autowired
    private TescoParser tescoParser;

    @Test
    public void testTescoParserItems() throws IOException {
//        List<TescoItem> tescoItemList = tescoParser.getItems().stream().filter(tescoItem -> tescoItem.getActualCost() != -1).toList();
    }
}
