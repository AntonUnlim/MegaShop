import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyData.fillMainData();
        Product product = Assortment.products.get(1);
        NewShop shop = new NewShop(product);

        Producer producer = new Producer(shop, product);
        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            consumers.add(new Consumer(shop, product, "Consumer_" + i));
        }

        producer.start();
        for(Consumer c : consumers) {
            c.start();
        }

        producer.join();
        for(Consumer c : consumers) {
            c.join();
        }
    }
}
