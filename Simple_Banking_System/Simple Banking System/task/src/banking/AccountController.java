package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountController {

    private final Random random = new Random();
    private final SQLiteDataSource dataSource = new SQLiteDataSource();
    private final Set<String> cardNumbers = new HashSet<>();

    AccountController(String filename) {
        dataSource.setUrl("jdbc:sqlite:" + filename);

        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT NOT NULL," +
                "pin TEXT NOT NULL," +
                "balance INTEGER NOT NULL DEFAULT 0);"
            );
            ResultSet numbers = statement.executeQuery("SELECT number FROM card;");
            while (numbers.next()) {
                cardNumbers.add(numbers.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String[] createAccount() {
        String cardNumber = createCardNumber();
        String pin = String.format("%04d", random.nextInt(10_000));
        cardNumbers.add(cardNumber);

        String sql = "INSERT INTO card (number, pin) VALUES (?, ?);";
        try (PreparedStatement pStatement = dataSource.getConnection().prepareStatement(sql)) {
            pStatement.setString(1, cardNumber);
            pStatement.setString(2, pin);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[] {cardNumber, pin};
    }

    private String createCardNumber() {
        // 6 iin + 9 account number + 1 check digit = 16 digits
        char[] iin = "400000".toCharArray();
        char[] cardNumber = new char[16];
        System.arraycopy(iin, 0, cardNumber, 0, iin.length);
        String cardNumberStr;
        do {
            for (int i = iin.length; i < cardNumber.length - 1; i++) {
                cardNumber[i] = (char) ('0' + random.nextInt(10));
            }
            cardNumber[cardNumber.length - 1] = getCheckDigit(cardNumber);
            cardNumberStr = String.valueOf(cardNumber);
        } while (cardNumbers.contains(cardNumberStr));
        return cardNumberStr;
    }

    private char getCheckDigit(char[] number) {
        // Luhn algorithm
        int sum = 0;
        for (int i = 0; i < number.length - 1; i++) {
            int x = Character.getNumericValue(number[i]);
            if (i % 2 == 0) {
                // Double the digits at even indices because the loop starts at 0.
                // Those are the odd digits mentioned in the task description.
                x += x;
                if (x > 9) {
                    x -= 9;
                }
            }
            sum += x;
        }
        if (sum % 10 == 0) {
            return '0';
        } else {
            int nextMultipleOf10 = (sum / 10 + 1) * 10;
            return (char) ('0' + nextMultipleOf10 - sum);
        }
    }

    boolean checkCardAndPin(String cardNumber, String pin) {
        if (!cardNumbers.contains(cardNumber)) {
            return false;
        }
        String sql = "SELECT pin FROM card WHERE number = ?;";
        try (PreparedStatement pStatement = dataSource.getConnection().prepareStatement(sql)) {
            pStatement.setString(1, cardNumber);
            ResultSet pins = pStatement.executeQuery();
            return pin.equals(pins.getString("pin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    int getBalance(String cardNumber) {
        String sql = "SELECT balance FROM card WHERE number = ?;";
        try (PreparedStatement pStatement = dataSource.getConnection().prepareStatement(sql)) {
            pStatement.setString(1, cardNumber);
            ResultSet resultBalance = pStatement.executeQuery();
            return resultBalance.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    void addIncome(String cardNumber, int amount) {
        String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
        try (PreparedStatement pStatement = dataSource.getConnection().prepareStatement(sql)) {
            pStatement.setInt(1, amount);
            pStatement.setString(2, cardNumber);
            pStatement.executeUpdate();
            System.out.println("Income was added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void closeAccount(String cardNumber) {
        String sql = "DELETE FROM card WHERE number = ?";
        try (PreparedStatement pStatement = dataSource.getConnection().prepareStatement(sql)) {
            pStatement.setString(1, cardNumber);
            pStatement.executeUpdate();
            cardNumbers.remove(cardNumber);
            System.out.println("\nThe account has been closed!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean checkInvalidTargetCardNumber(String targetCardNumber) {
        char[] digits = targetCardNumber.toCharArray();
        char checkDigit = getCheckDigit(digits);
        if (checkDigit != digits[digits.length - 1]) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return true;
        }
        if (cardNumbers.contains(targetCardNumber)) {
            return false;
        }
        System.out.println("Such a card does not exist.");
        return true;
    }

    void transfer(String fromCardNumber, String toCardNumber, int amount) {
        String takeMoney = "UPDATE card SET balance = balance - ? WHERE number = ?";
        String addMoney = "UPDATE card SET balance = balance + ? WHERE number = ?";
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement takeStatement = con.prepareStatement(takeMoney);
                 PreparedStatement addStatement = con.prepareStatement(addMoney)) {
                takeStatement.setInt(1, amount);
                takeStatement.setString(2, fromCardNumber);
                takeStatement.executeUpdate();

                addStatement.setInt(1, amount);
                addStatement.setString(2, toCardNumber);
                addStatement.executeUpdate();

                con.commit();

                System.out.println("Success!");
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    con.rollback();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
