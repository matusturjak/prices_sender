package sk.matusturjak.price_sender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.matusturjak.price_sender.mapper.ItemMapper;
import sk.matusturjak.price_sender.dto.ItemDto;
import sk.matusturjak.price_sender.model.db.Item;
import sk.matusturjak.price_sender.repository.impl.ItemRepositoryImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepositoryImpl itemRepository;

    public void storeItem(ItemDto itemDto) {
        Item findItem = itemRepository.findByName(itemDto.getName());

        if (findItem == null) {
            itemRepository.saveItem(ItemMapper.mapItemDtoToItem(itemDto));
            log.info("Item with name {} stored to DB", itemDto.getName());
        } else {
            itemRepository.updateItem(findItem.getId(), ItemMapper.itemDtoToMap(itemDto));
            log.info("Item with id {} updated in DB", findItem.getId());
        }
    }
}
