package sk.matusturjak.price_sender.config.rabbitmq;

public enum RoutingKey {
    TESCO_ITEM_STORE("tesco.store"),
    TESCO_ITEM_STORE_ERROR("tesco.store.err");
    private String name;

    RoutingKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
