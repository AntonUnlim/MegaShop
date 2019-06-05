package common;

import task1.Shop;

import java.util.HashMap;
import java.util.Map;

public class City {
    private int ID;
    private String name;
    private Map<Integer, Shop> shops;

    public City(int cityID, String name) {
        this.ID = cityID;
        this.name = name;
        shops = new HashMap<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Shop> getShops() {
        return shops;
    }

    public void addShop(Shop shop) {
        shops.put(shop.getID(), shop);
    }

    @Override
    public String toString() {
        return "common.City: ID - " + ID + ", Name - " + name;
    }
}
