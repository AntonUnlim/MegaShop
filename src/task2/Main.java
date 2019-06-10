package task2;

import common.Assortment;
import common.MyData;
import common.Product;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyData.fillMainData();
        Product product = Assortment.products.get(1);
        Shop shop = new Shop(product);

        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new Producer(shop, product)));

        for (int i = 0; i < 10; i++) {
            Consumer consumer = new Consumer(shop, product, "Consumer_" + i);
            shop.addConsumer(consumer);
            threads.add(new Thread(consumer));
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }
    }
}
