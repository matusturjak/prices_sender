package sk.matusturjak.price_sender.mapper;

import org.bson.types.ObjectId;
import sk.matusturjak.price_sender.dto.ItemDto;
import sk.matusturjak.price_sender.model.db.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    private ItemMapper() {}

    public static List<ItemDto> mapItemsToItemsDtos(List<Item> items) {
        return items
                .stream()
                .map(item -> {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setName(item.getName());
                    itemDto.setNormalCost(item.getNormalCost());
                    itemDto.setActualCost(item.getActualCost());
                    itemDto.setValidTo(item.getValidTo());
                    itemDto.setPictureURL(item.getPictureURL());
                    itemDto.setSuperDepartment(item.getSuperDepartment());
                    itemDto.setDepartment(item.getDepartment());

                    return itemDto;
                })
                .collect(Collectors.toList());
    }

    public static List<ObjectId> mapItemsToReferences(List<Item> items) {
        return items
                .stream()
                .map(Item::getId)
                .collect(Collectors.toList());
    }

    public static Item mapItemDtoToItem(ItemDto itemDto) {
        Item item = new Item();

        item.setName(itemDto.getName());
        item.setNormalCost(itemDto.getNormalCost());
        item.setActualCost(itemDto.getActualCost());
        item.setValidTo(itemDto.getValidTo());
        item.setPictureURL(itemDto.getPictureURL());
        item.setSuperDepartment(itemDto.getSuperDepartment());
        item.setDepartment(itemDto.getDepartment());

        return item;
    }
}
