package task2;

import common.Product;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Shop {
    private volatile BlockingQueue<Product> store;
    private volatile BlockingQueue<Consumer> consumers;

    public Shop(Product product) throws InterruptedException {
        store = new LinkedBlockingQueue<>(12);
        for(int i = 0; i < 10; i++)
            store.put(product);
        consumers = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<Product> getStore() {
        return store;
    }

    public int getStoreSize() {
        return store.size();
    }

    public int getConsumersSize() {
        return consumers.size();
    }

    public void addConsumer(Consumer consumer) throws InterruptedException {
        this.consumers.put(consumer);
    }

    public void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }
}
