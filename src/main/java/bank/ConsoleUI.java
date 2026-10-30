package bank;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    /*
    Authorization
    1. Do you want login or register;
        1. If "register", should or create new JSON file or add out user to old JSON file
           To add to old file should make map from file and add new item and then write all to out JSON file.
               1. Check that in out MAP we don't have that username. If we have, then "login" or "try again";
        2. If "login" we should ask for the username, create MAP of all users.
           1. If we don't have user with this username:
               1. Say that user wrote the wrong username and say "Try one more time"
               2. Option to create new account with that username.
     */
    private static final String[] MENU_ITEMS = new String[] {
            "1. Add income",
            "2. Add consumption",
            "3. Show transaction history",
            "4. Statistics by category",
            "5. Save and exit"
    };
    private final Scanner scanner;
    private final FileStorage storage;

    public ConsoleUI() throws IOException {
        this.scanner = new Scanner(System.in);
        this.storage = FileStorage.createFile();
    }

    public void start() {
        authorizationUI();
//        do{
//            showFunction();
//            choice = askFunction();
//            doAction(choice);
//        } while(choice != 5);
    }

    private void authorizationUI(){
        String choice;
        boolean isValid;
        do{
            System.out.print("Do you want to register(1) or login(2): ");
            choice = scanner.nextLine().trim();

            try{
                int choiceInt = Integer.parseInt(choice);
                isValid = (choiceInt == 1 || choiceInt == 2);
                if (isValid){
                    switch (choiceInt){
                        case 1 -> {
                            registration();
                        }
                        case 2 -> {
                            login();
                        }
                    }
                } else {
                    System.out.println("Wrong input. 1 - register, 2 - login");
                }
            } catch (NumberFormatException | IOException e){
                isValid = false;
                System.out.println("Wrong input. You should write numbers(1 or 2)!");
            }

        } while(!isValid);
    }

    private void registration() throws IOException {
        boolean isValid;
        String email;
        String password;
        String fullName;
        do{
            System.out.print("Enter your own email or ('login'): ");
            email = scanner.nextLine();
            isValid = User.checkEmail(storage, email) || email.equals("login");
            if (!isValid){
                System.out.println("We already have this email in our system!\n" +
                        "If you want enter your account, write 'login'\n" +
                        "Or try to enter an another email to create new account");
            } else {
                if (email.equals("login")){
                    login();
                    break;
                }
                System.out.print("Enter a password for your account: ");
                password = scanner.nextLine();
                System.out.print("Enter your full name: ");
                fullName = scanner.nextLine();
                User.registerUser(storage, email, password, fullName);
            }
        } while(!isValid);
    }

    private void login() throws IOException {
        boolean isValid;
        String reply;
        String password;
        do {
            System.out.print("Enter your email or 'register': ");
            reply = scanner.nextLine();
            isValid = User.checkEmail(storage, reply) || reply.equals("register");
            if (!isValid){
                System.out.println("We don't have this email in out system.\n" +
                        "Try again or write 'register' to create new account");
            } else {
                if (reply.equals("register")){
                    registration();
                    break;
                }
                password = checkPassUI();
                System.out.println(password);
            }
        } while(!isValid);
    }

    private String checkPassUI(){
        boolean isValid;
        String password;
        do{
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            isValid = User.checkPass();
            if (!isValid){
                System.out.println("Wrong password. Try again!");
            }
        } while(!isValid);
        return password;
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