package task2;

import common.Product;

public class Producer extends Thread {
    private NewShop shop;
    private Product product;

    public Producer(NewShop shop, Product product) {
        this.shop = shop;
        this.product = product;
    }

    public void addProduct() throws InterruptedException {
        while (true) {
            synchronized (shop) {
                while (shop.getStore().size() == shop.storeCapacity()) {
                    shop.wait();
                    FileLog.writeToFile("task2.Producer waiting..." + "\n");
                    FileLog.writeToFile("Amount of products in store - " + shop.getStore().size() + "\n");
                }
                FileLog.writeToFile("task2.Producer produced - " + product.getName() + "\n");
                shop.getStore().add(product);
                FileLog.writeToFile("Amount of products in store - " + shop.getStore().size() + "\n");
                shop.notify();
            }
        }
    }

    @Override
    public void run() {
        try {
            addProduct();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
