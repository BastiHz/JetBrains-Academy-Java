package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class SimpleBankingSystem {
    private final Map<String, Account> accounts = new HashMap<>();
    private final String IIN = "400000";
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private Account currentAccount;

    void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "0":
                    running = false;
                    break;
                case "1":
                    createAccount();
                    break;
                case "2":
                    if (logIn()) {
                        running = accountMenu();
                    }
                    break;
                default:
                    System.out.println("\nInvalid input. Please type a number between 0 and 2.\n");
                    break;
            }
        }
        System.out.println("\nBye!");
    }

    private boolean accountMenu() {
        System.out.println("\nYou have successfully logged in!");
        while (true) {
            System.out.println("\n1. Balance");
            System.out.println("2. Log out");
            System.out.println("0. Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "0":
                    currentAccount = null;
                    return false;
                case "1":
                    System.out.println("\nBalance: " + currentAccount.getBalance());
                    break;
                case "2":
                    currentAccount = null;
                    System.out.println("\nYou have successfully logged out!\n");
                    return true;
                default:
                    System.out.println("\nInvalid input. Please type a number between 0 and 2.");
                    break;
            }
        }
    }

    private boolean logIn() {
        System.out.println("\nEnter your card number:");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        Account account = accounts.get(cardNumber);
        if (account != null && account.checkPin(pin)) {
            currentAccount = account;
            return true;
        }
        System.out.println("\nWrong card number or PIN!\n");
        return false;
    }

    private void createAccount() {
        String cardNumber = createCardNumber();
        String pin = String.format("%04d", random.nextInt(10_000));
        accounts.put(cardNumber, new Account(cardNumber, pin));
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(cardNumber);
        System.out.println("Your PIN:");
        System.out.println(pin + "\n");
    }

    private String createCardNumber() {
        // 6 iin + 9 account number + 1 check digit = 16 digits
        String cardNumber;
        do {
            String accountNumber = String.format("%09d", random.nextInt(1_000_000_000));
            cardNumber = IIN + accountNumber + random.nextInt(10);
        } while(accounts.containsKey(cardNumber));
        return cardNumber;
    }
}
