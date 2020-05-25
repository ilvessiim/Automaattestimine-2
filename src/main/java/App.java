import servlet.JettyServer;

public class App {

    public static void main(String[] args) {

        JettyServer jettyServer = new JettyServer();

        try {
            jettyServer.start();
        } catch (Exception ex) {
            System.out.println("Something went horribly wrong");
        }

    }

}