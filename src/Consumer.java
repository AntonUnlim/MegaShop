import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread {
    private NewShop shop;
    private Product product;
    private String name;
    private List<Product> listOfProducts;

    public Consumer(NewShop shop, Product product, String name) {
        this.shop = shop;
        this.product = product;
        this.name = name;
        listOfProducts = new ArrayList<>();
    }

    public void getProduct() throws InterruptedException {
        while (true) {
            synchronized (shop) {
                while(shop.getStore().size() == 0) {
                    shop.wait();
                    FileLog.writeToFile(name + " waiting...\n");
                    FileLog.writeToFile("Amount of products in store - " + shop.getStore().size() + "\n");
                }
                listOfProducts.add(shop.getStore().remove());
                FileLog.writeToFile(name + " consumed - " + product.getName() + "\n");
                FileLog.writeToFile("Amount of products in store - " + shop.getStore().size() + "\n");
                FileLog.writeToFile("Amount of products in list - " + listOfProducts.size() + "\n");
                shop.notify();
            }
        }
    }

    @Override
    public void run() {
        try {
            getProduct();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
