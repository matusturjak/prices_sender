package sk.matusturjak.price_sender.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.matusturjak.price_sender.mapper.ItemMapper;
import sk.matusturjak.price_sender.dto.ItemDto;
import sk.matusturjak.price_sender.model.db.Item;
import sk.matusturjak.price_sender.repository.impl.ItemRepositoryImpl;

import java.util.HashMap;

@Slf4j
@Service
public class ItemService {

    private final ItemRepositoryImpl itemRepository;

    public ItemService(ItemRepositoryImpl itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void storeItem(ItemDto itemDto) {
        Item findItem = itemRepository.findByName(itemDto.getName());

        if (findItem == null) {
            itemRepository.saveItem(ItemMapper.mapItemDtoToItem(itemDto));
            log.info("Item with name {} stored to DB", itemDto.getName());
        } else {
            //TODO object mapper ItemDto -> Map using jackson
            itemRepository.updateItem(findItem.getId(), new HashMap<>());
        }
    }
}
