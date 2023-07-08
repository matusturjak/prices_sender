package sk.matusturjak.price_sender.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "item.exchange";
    public static final String TESCO_ITEM_STORE_QUEUE_NAME = "tesco.item.store";
    public static final String TESCO_ITEM_STORE_ERR_QUEUE_NAME = "tesco.item.store.err";

//    @Bean
//    public DirectExchange direct() {
//        return new DirectExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Queue tescoItemStoreQueue() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding binding1a(DirectExchange direct,
//                             Queue tescoItemStoreQueue) {
//        return BindingBuilder.bind(tescoItemStoreQueue)
//                .to(direct)
//                .with(RoutingKey.TESCO_ITEM_STORE.getRoutingKey());
//    }

    @Bean(name = "itemExchange")
    public DirectExchange itemExchange() {
        return ExchangeBuilder
                .directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean(name = "tescoItemStoreQueue")
    public Queue tescoItemStoreQueue() {
        return QueueBuilder
                .durable(TESCO_ITEM_STORE_QUEUE_NAME)
                .lazy()
                .build();
    }

    @Bean
    public Binding tescoItemStoreBinding() {
        return BindingBuilder
                .bind(tescoItemStoreQueue())
                .to(itemExchange())
                .with(RoutingKey.TESCO_ITEM_STORE.getName());
    }

    @Bean(name = "tescoItemStoreQueue")
    public Queue tescoItemStoreErrorQueue() {
        return QueueBuilder
                .durable(TESCO_ITEM_STORE_ERR_QUEUE_NAME)
                .lazy()
                .build();
    }

    @Bean
    public Binding tescoItemStoreErrorBinding() {
        return BindingBuilder
                .bind(tescoItemStoreQueue())
                .to(itemExchange())
                .with(RoutingKey.TESCO_ITEM_STORE_ERROR.getName());
    }
}
