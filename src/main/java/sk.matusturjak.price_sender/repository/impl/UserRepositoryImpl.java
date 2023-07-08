package sk.matusturjak.price_sender.repository.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.model.db.User;
import sk.matusturjak.price_sender.repository.UserRepository;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private static final String COLLECTION_NAME = "users";
    private MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class, COLLECTION_NAME);
    }
}
