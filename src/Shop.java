import java.util.HashMap;
import java.util.Map;

public class Shop {
    private int ID;
    private String name;
    private String address;
    private Map<Integer, Integer> products;

    public Shop(int shopID, String name, String address) {
        this.ID = shopID;
        this.name = name;
        this.address = address;
        products = new HashMap<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product, int amn) {
        products.put(product.getID(), amn);
    }

    public synchronized boolean sellProduct(Product product, int amn) {
        int currentAmn = products.get(product.getID());
        if (currentAmn < amn || currentAmn <= 0) return false;
        products.put(product.getID(), currentAmn - amn);
        return true;
    }

    @Override
    public String toString() {
        return "Shop: ID - " + ID + ", Name - " + name + ", address - " + address;
    }
}
