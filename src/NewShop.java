import java.util.LinkedList;
import java.util.Queue;

public class NewShop {
    private Queue<Product> store;
    private final int CAPACITY = 100;

    public NewShop(Product product) {
        store = new LinkedList<>();
        for(int i = 0; i < 10; i++)
            store.add(product);
    }

    public Queue<Product> getStore() {
        return store;
    }

    public int storeCapacity() {
        return CAPACITY;
    }
}
