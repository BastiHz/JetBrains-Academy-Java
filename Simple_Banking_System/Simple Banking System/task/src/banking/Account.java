package banking;

class Account {
    private final String cardNumber;
    private final String pin;
    private int balance = 0;

    Account(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    boolean checkPin(String pin) {
        return this.pin.equals(pin);
    }

    int getBalance() {
        return balance;
    }
}
