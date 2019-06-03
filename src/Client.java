import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Client extends Thread {
    private String name;
    private Map<Product, Integer> cart;
    private float cash;

    private String isExit = "";
    private Shop selectedShop;
    private Scanner input = new Scanner(System.in);

    public Client(String name) {
        this.name = name;
        cart = new HashMap<>();

        cash = Math.round((new Random()).nextFloat()*1000);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public float getCash() {
        return cash;
    }

    public String getClientName() {
        return name;
    }

    public synchronized boolean buyProduct(Product product, int amn) {
        if (product.getPrice() * amn > cash) return false;
        if(cart.get(product) != null) {
            cart.put(product, cart.get(product) + amn);
        }
        else {
            cart.put(product, amn);
        }
        cash -= product.getPrice() * amn;
        return true;
    }

    public void shopping() {
        System.out.println(getName() + ", you have " + getCash() + " cash");

        /*System.out.println("What city, from the list below, do you wish to visit? (input city ID)");
        for (City city : MyData.getCities().values()) {
            System.out.println(city);
        }*/

        int selectedCityID = 1; //input.nextInt();
        City selectedCity = MyData.getCities().get(selectedCityID);
        System.out.println(getName() + ", you have arrived to " + selectedCity.getName() + "!");

        /*System.out.println("Please, choose the shop that you want to visit? (input shop ID)");
        for (Shop shop : selectedCity.getShops().values()) {
            System.out.println(shop);
        }*/

        int selectedShopID = 1; //input.nextInt();
        selectedShop = selectedCity.getShops().get(selectedShopID);

        System.out.println(getName() + ", you have entered " + selectedShop.getName() + " shop");

        //System.out.println("Select product and amount, that you want to buy (input product ID,Amn)." +
        //        "\nPrint EXIT after you finish buying\n");
    }

    @Override
    public void interrupt() {
        super.interrupt();
        input.close();
    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            //System.out.println("Products available:");
            /*for (Product product : selectedShop.getProducts().keySet()) {
                System.out.println(product + " Available amount - " + selectedShop.getProducts().get(product));
            }*/
            //System.out.println("Enter productID and Amount (separated with comma)");
            //isExit = input.next();

            /*if (isExit.toUpperCase().equals("EXIT")) {
                System.out.println("Good bye!");
                break;
            }*/
            //String[] inputString = isExit.split(",");
            //int selectedProductID = Integer.parseInt(inputString[0]);
            int selectedProductID = rand.nextInt(5) + 1;
            System.out.println(getClientName() + " generated " + selectedProductID);
            //int selectedAmount = Integer.parseInt(inputString[1]);
            int selectedAmount = 1;
            Product selectedProduct = Assortment.products.get(selectedProductID);
            if (selectedShop.sellProduct(selectedProduct, selectedAmount)) {
                if (buyProduct(selectedProduct, selectedAmount)) {
                    //System.out.println("Success! Enter next product or Exit");
                    System.out.println(getClientName() + " has bought " + selectedProduct.getName());
                } else {
                    //System.out.println("You have not enough money");
                    System.out.println(getClientName() + " out of money");
                    break;
                }
            } else {
                System.out.println("Not enough!");
            }

            //System.out.println("Cash left: " + getCash());
            System.out.println(getClientName() + " has " + getCash() + " cash left");
            /*System.out.println("the content of the cart:");
            if (getCart().size() == 0) System.out.println("Empty!");
            else {
                for (Product product : getCart().keySet()) {
                    System.out.println(product + ", amount - " + getCart().get(product) + "\n");
                }
            }*/
        }
    }
}
