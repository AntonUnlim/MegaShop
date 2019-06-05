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
                while (shop.getStore().size() == shop.storeCapacity())
                    shop.wait();
                System.out.println("Producer produced - " + product.getName());
                shop.getStore().add(product);
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
