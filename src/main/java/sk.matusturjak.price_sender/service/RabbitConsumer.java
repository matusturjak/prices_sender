package sk.matusturjak.price_sender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.config.rabbitmq.RabbitConfig;
import sk.matusturjak.price_sender.dto.ItemDto;

@Slf4j
@AllArgsConstructor
@Component
public class RabbitConsumer {
    private final ItemService itemService;

    @RabbitListener(queues = RabbitConfig.TESCO_ITEM_STORE_QUEUE_NAME)
    public void tescoItemStoreListen(@Payload ItemDto itemDto) {
        itemService.storeItem(itemDto);
    }
}
