package task1;

import common.MyData;

public class Program {
    public static void main(String[] args) {
        MyData.fillMainData();

        for (int i = 0; i <100; i++) {
            Client client = new Client("Bob_" + i);
            client.shopping();
            client.start();
        }
    }
}
