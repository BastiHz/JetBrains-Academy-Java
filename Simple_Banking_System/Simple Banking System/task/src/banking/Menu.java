package banking;

import java.util.Scanner;

class Menu {

    protected final Scanner scanner = new Scanner(System.in);
    protected final AccountController accountController;

    Menu(String dataBasePath) {
        accountController = new AccountController(dataBasePath);
    }

    void run() {
        runMainMenu();
        System.out.println("\nBye!");
    }

    void runMainMenu() {
        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "0":
                    return;
                case "1":
                    String[] cardAndPin = accountController.createAccount();
                    System.out.println("\nYour card has been created");
                    System.out.println("Your card number:");
                    System.out.println(cardAndPin[0]);
                    System.out.println("Your PIN:");
                    System.out.println(cardAndPin[1] + "\n");
                    break;
                case "2":
                    System.out.println("\nEnter your card number:");
                    String cardNumber = scanner.nextLine();
                    System.out.println("Enter your PIN:");
                    String pin = scanner.nextLine();
                    if (accountController.checkCardAndPin(cardNumber, pin)) {
                        boolean exit = runAccountMenu(cardNumber);
                        if (exit) {
                            return;
                        }
                    } else {
                        System.out.println("\nWrong card number or PIN!\n");
                    }
                    break;
                default:
                    System.out.println("\nInvalid input. Please type a number between 0 and 2.\n");
                    break;
            }
        }
    }

    boolean runAccountMenu(String cardNumber) {
        System.out.println("\nYou have successfully logged in!");
        while (true) {
            System.out.println("\n1. Balance");
            System.out.println("2. Log out");
            System.out.println("0. Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "0":
                    return true;
                case "1":
                    System.out.println("\nBalance: " + accountController.getBalance(cardNumber));
                    break;
                case "2":
                    System.out.println("\nYou have successfully logged out!\n");
                    return false;
                default:
                    System.out.println("\nInvalid input. Please type a number between 0 and 2.");
                    break;
            }
        }
    }
}
