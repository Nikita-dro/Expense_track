package bank;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("=======Your Bank=======");
//        User.authorization();
        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}