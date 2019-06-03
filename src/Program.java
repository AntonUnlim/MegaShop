public class Program {
    public static void main(String[] args) {
        MyData.fillMainData();

        Client client1 = new Client("Bob");
        Client client2 = new Client("Jon");
        Client client3 = new Client("Ben");
        client1.shopping();
        client2.shopping();
        client3.shopping();
        client1.start();
        client2.start();
        client3.start();
    }
}
