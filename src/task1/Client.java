package task1;

import common.Assortment;
import common.City;
import common.MyData;
import common.Product;

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
        System.out.println(getClientName() + "\t, you have \t" + getCash() + "\t cash");

        int selectedCityID = 1;
        City selectedCity = MyData.getCities().get(selectedCityID);
        System.out.println(getClientName() + "\t, you have arrived to \t" + selectedCity.getName() + "\t!");

        int selectedShopID = 1;
        selectedShop = selectedCity.getShops().get(selectedShopID);

        System.out.println(getClientName() + "\t, you have entered \t" + selectedShop.getName() + "\t shop");

    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            int selectedProductID = rand.nextInt(5) + 1;
            int selectedAmount = 1;
            Product selectedProduct = Assortment.products.get(selectedProductID);
            System.out.println(getClientName() + "\t generated \t" + selectedProduct.getName());
            if (selectedShop.sellProduct(selectedProduct, selectedAmount)) {
                if (buyProduct(selectedProduct, selectedAmount)) {
                    System.out.println(getClientName() + "\t has bought \t" + selectedProduct.getName());
                } else {
                    System.out.println(getClientName() + "\t out of money");
                    break;
                }
            } else {
                System.out.println(getClientName() + "\tNot enough!\t" + selectedProduct.getName());
                break;
            }

            System.out.println(getClientName() + "\t has \t" + getCash() + "\t cash left");
        }
    }
}
