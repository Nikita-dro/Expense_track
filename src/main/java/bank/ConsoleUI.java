package bank;

import java.util.Scanner;

public class ConsoleUI {
    private static final String[] MENU_ITEMS = new String[] {
            "1. Add income",
            "2. Add consumption",
            "3. Show transaction history",
            "4. Statistics by category",
            "5. Save and exit"
    };
    private final Scanner scanner;

    public ConsoleUI(){
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        int choice;
        do{
            showFunction();
            choice = askFunction();
            doAction(choice);
        } while(choice != 5);
    }

    private void showFunction(){
        for (String function : MENU_ITEMS){
            System.out.println(function);
        }
    }

    private int askFunction(){
        while(true){
            System.out.print("Choose a function (1-5): ");
            String reply = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(reply);
                if (choice >= 1 && choice <= 5){
                    return choice;
                }
                System.out.println("Write a number (1-5)!");
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Write a number");
            }
        }
    }

    private void doAction(int choice){
        switch (choice){
            case 1 -> WalletService.addIncome();
            case 2 -> WalletService.addConsumption();
            case 3 -> WalletService.showHistory();
            case 4 -> WalletService.statistic();
            case 5 -> WalletService.exit();
        }
    }


}