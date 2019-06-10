package task2;

import common.Product;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private Product product;
    private final BlockingQueue<Product> queue;
    private Shop shop;

    public Producer(Shop shop, Product product) {
        this.product = product;
        this.queue = shop.getStore();
        this.shop = shop;
    }

    public void addProduct() throws InterruptedException {
        while (shop.getConsumersSize() > 0) {
            FileLog.writeToFile(System.currentTimeMillis() + ";Producer;produced - " + product.getName() + "\n");
            queue.put(product);
            FileLog.writeToFile(System.currentTimeMillis() + ";Producer;products in store - " + shop.getStoreSize() + "\n");
            FileLog.writeToFile(System.currentTimeMillis() + ";Producer;consumers in shop - " + shop.getConsumersSize() + "\n");
        }
    }

    @Override
    public void run() {
        try {
            FileLog.writeToFile(System.currentTimeMillis() + ";Producer;started\n");
            addProduct();
            FileLog.writeToFile(System.currentTimeMillis() + ";Producer;finished\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
