package sk.matusturjak.price_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sk.matusturjak.price_sender.repository.ItemRepository;
import sk.matusturjak.price_sender.repository.UserRepository;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories
public class PriceSenderApplication {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PriceSenderApplication.class, args);
    }
}
