package sk.matusturjak.price_sender.repository.impl;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.model.db.Item;
import sk.matusturjak.price_sender.repository.ItemRepository;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ItemRepositoryImpl implements ItemRepository {

    private static final String COLLECTION_NAME = "items";

    private MongoTemplate mongoTemplate;

    public ItemRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Item> findAll() {
        return mongoTemplate.findAll(Item.class, COLLECTION_NAME);
    }

    @Override
    public Item findByName(String name) {
        return mongoTemplate.find(
                Query.query(
                        Criteria.where("name").is(name)
                ), Item.class, COLLECTION_NAME
        ).stream().findFirst().orElse(null);
    }

    @Override
    public Item saveItem(Item item) {
        Item newItem = mongoTemplate.save(item);
        log.info("New Item created - {}", item.toString());
        return newItem;
    }

    @Override
    public void updateItem(ObjectId id, Map<String, Object> values) {
        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("_id").is(id)
                ), createUpdateDefinition(values), COLLECTION_NAME
        );

        log.info("Mongo document with id {} was updated", id.toString());
        log.info("Document {} update result: was acknowledged - {}", id, result.wasAcknowledged());
        log.info("Document {} update result: matched count - {}", id, result.getMatchedCount());
        log.info("Document {} update result: modified count - {}", id, result.getModifiedCount());
        log.info("Document {} update result: upserted id - {}", id, result.getUpsertedId());
    }

    private Update createUpdateDefinition(Map<String, Object> values) {
        Update update = new Update();
        values.forEach(update::addToSet);
        return update;
    }
}
