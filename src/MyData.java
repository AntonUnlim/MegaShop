import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyData {
    private static final String CITIES_PATH = "cities.csv";
    private static final String SHOPS_PATH = "shops.csv";
    private static final String PRODUCTS_PATH = "products.csv";
    private static final String ASSORTMENT_PATH = "assortment.csv";

    private static Scanner scanner;
    private static File file;

    private static Map<Integer, City> cities = new HashMap<>();

    public static void fillMainData() {
        fillAssortment();
        fillCities(cities);
        fillShops(cities);
        fillProducts(cities);
    }

    public static Map<Integer, City> getCities() {
        return cities;
    }

    private static void fillAssortment() {
        file = new File(ASSORTMENT_PATH);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tempStr = scanner.nextLine().trim();
                String[] splitedStr = tempStr.split(";");
                int ID = Integer.valueOf(splitedStr[0]);
                Product product = new Product(ID, splitedStr[1], Float.valueOf(splitedStr[2]));
                Assortment.products.put(ID, product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }

    private static void fillCities(Map<Integer, City> cities) {
        file = new File(CITIES_PATH);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tempStr = scanner.nextLine().trim();
                String[] splitedStr = tempStr.split(";");
                int ID = Integer.valueOf(splitedStr[0]);
                cities.put(ID, new City(ID, splitedStr[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }

    private static void fillShops(Map<Integer, City> cities) {
        file = new File(SHOPS_PATH);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tempStr = scanner.nextLine().trim();
                String[] splitedStr = tempStr.split(";");
                for(City city : cities.values()) {
                    if (city.getID() == Integer.parseInt(splitedStr[2])) {
                        city.addShop(new Shop(Integer.valueOf(splitedStr[0]), splitedStr[1], splitedStr[3]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }

    private static void fillProducts(Map<Integer, City> cities) {
        file = new File(PRODUCTS_PATH);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tempStr = scanner.nextLine().trim();
                String[] splitedStr = tempStr.split(";");
                for(City city : cities.values()) {
                    for (Shop shop : city.getShops().values()) {
                        int shopID = Integer.parseInt(splitedStr[0]);
                        int productID = Integer.parseInt(splitedStr[1]);
                        int amn = Integer.parseInt(splitedStr[2]);
                        if (shop.getID() == shopID) {
                            shop.addProduct(Assortment.products.get(productID), amn);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }
}
