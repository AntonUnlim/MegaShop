package task2;

import common.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private Shop shop;
    private Product product;
    private String name;
    private List<Product> listOfProducts;
    private final BlockingQueue<Product> queue;

    public Consumer(Shop shop, Product product, String name) {
        this.shop = shop;
        this.product = product;
        this.name = name;
        listOfProducts = new ArrayList<>();
        this.queue = shop.getStore();
    }

    public void getProduct() throws InterruptedException {
        while (listOfProducts.size() < 10) {
            listOfProducts.add(queue.take());
            FileLog.writeToFile(System.currentTimeMillis() + ";" + name + ";consumed - " + product.getName() + "\n");
            FileLog.writeToFile(System.currentTimeMillis() + ";" + name + ";products in store - " + shop.getStoreSize() + "\n");
            FileLog.writeToFile(System.currentTimeMillis() + ";" + name + ";products in list - " + listOfProducts.size() + "\n");
        }
    }

    @Override
    public void run() {
        try {
            FileLog.writeToFile(System.currentTimeMillis() + ";" + name + ";started\n");
            getProduct();
            shop.removeConsumer(this);
            FileLog.writeToFile(System.currentTimeMillis() + ";" + name + ";finished\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
