package sk.matusturjak.price_sender.repository;

import org.bson.types.ObjectId;
import sk.matusturjak.price_sender.model.db.Item;

import java.util.List;
import java.util.Map;

public interface ItemRepository {
    List<Item> findAll();
    Item findByName(String name);
    Item saveItem(Item item);
    void updateItem(ObjectId id, Map<String, Object> values);
}
