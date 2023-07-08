package sk.matusturjak.price_sender.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.rabbitmq.queues")
public class RabbitMQProperties {

    private String itemsQueueName;
    private String logsQueueName;

    public String getItemsQueueName() {
        return itemsQueueName;
    }

    public void setItemsQueueName(String itemsQueueName) {
        this.itemsQueueName = itemsQueueName;
    }

    public String getLogsQueueName() {
        return logsQueueName;
    }

    public void setLogsQueueName(String logsQueueName) {
        this.logsQueueName = logsQueueName;
    }
}
