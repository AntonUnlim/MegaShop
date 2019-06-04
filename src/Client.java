import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client extends Thread {
    private String name;
    private Map<Integer, Integer> productBasket;
    private float cash;

    private Shop selectedShop;

    public Client(String name) {
        this.name = name;
        productBasket = new HashMap<>();

        cash = Math.round((new Random()).nextFloat()*1000);
    }

    public float getCash() {
        return cash;
    }

    public String getClientName() {
        return name;
    }

    public synchronized boolean buyProduct(Product product, int amn) {
        if (product.getPrice() * amn > cash) return false;
        if(productBasket.containsKey(product.getID())) {
            productBasket.put(product.getID(), productBasket.get(product.getID()) + amn);
        }
        else {
            productBasket.put(product.getID(), amn);
        }
        cash -= product.getPrice() * amn;
        return true;
    }

    public void shopping() {
        System.out.println(getName() + ", you have " + getCash() + " cash");

        int selectedCityID = 1;
        City selectedCity = MyData.getCities().get(selectedCityID);
        System.out.println(getName() + ", you have arrived to " + selectedCity.getName() + "!");

        int selectedShopID = 1;
        selectedShop = selectedCity.getShops().get(selectedShopID);

        System.out.println(getName() + ", you have entered " + selectedShop.getName() + " shop");

    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
           int selectedProductID = rand.nextInt(5) + 1;
            System.out.println(getClientName() + " generated " + selectedProductID);
            int selectedAmount = 1;
            Product selectedProduct = Assortment.products.get(selectedProductID);
            if (selectedShop.sellProduct(selectedProduct, selectedAmount)) {
                if (buyProduct(selectedProduct, selectedAmount)) {
                    System.out.println(getClientName() + " has bought " + selectedProduct.getName());
                } else {
                    System.out.println(getClientName() + " out of money");
                    break;
                }
            } else {
                System.out.println("Not enough!");
            }

            System.out.println(getClientName() + " has " + getCash() + " cash left");
        }
    }
}
