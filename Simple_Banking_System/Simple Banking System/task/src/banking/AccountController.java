package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountController {

    private final Random random = new Random();
    private final SQLiteDataSource dataSource = new SQLiteDataSource();
    private final Set<String> cardNumbers = new HashSet<>();

    AccountController(String filename) {
         dataSource.setUrl("jdbc:sqlite:" + filename);
         connect();
    }

    private void connect() {
        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT NOT NULL," +
                "pin TEXT NOT NULL," +
                "balance INTEGER DEFAULT 0);"
            );
            ResultSet numbers = statement.executeQuery("SELECT number FROM card;");
            while (numbers.next()) {
                cardNumbers.add(numbers.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean checkCardAndPin(String cardNumber, String pin) {
        if (!cardNumbers.contains(cardNumber)) {
            return false;
        }
        try (Statement statement = dataSource.getConnection().createStatement()) {
            String sql = String.format("SELECT pin FROM card WHERE number = '%s';", cardNumber);
            ResultSet pins = statement.executeQuery(sql);
            return pin.equals(pins.getString("pin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    String[] createAccount() {
        String cardNumber = createCardNumber();
        String pin = String.format("%04d", random.nextInt(10_000));
        cardNumbers.add(cardNumber);

        try (Statement statement = dataSource.getConnection().createStatement()) {
            String sql = String.format(
                "INSERT INTO card (number, pin) VALUES ('%s', '%s');",
                cardNumber,
                pin
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[] {cardNumber, pin};
    }

    private String createCardNumber() {
        // 6 iin + 9 account number + 1 check digit = 16 digits
        String cardNumber;
        String iin = "400000";
        do {
            StringBuilder stringBuilder = new StringBuilder(16);
            stringBuilder.append(iin);
            for (int i = 0; i < 9; i++) {
                stringBuilder.append(random.nextInt(10));
            }
            appendCheckDigit(stringBuilder);
            cardNumber = stringBuilder.toString();
        } while (cardNumbers.contains(cardNumber));
        return cardNumber;
    }

    private void appendCheckDigit(StringBuilder stringBuilder) {
        // Luhn algorithm
        int sum = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            int x = Character.getNumericValue(stringBuilder.charAt(i));
            if (i % 2 == 0) {
                // Modify digits at even indices because the loop starts at 0.
                // Those are the odd digits mentioned in the task description.
                x += x;
                if (x > 9) {
                    x -= 9;
                }
            }
            sum += x;
        }
        if (sum % 10 == 0) {
            stringBuilder.append(0);
        } else {
            int nextMultipleOf10 = (sum / 10 + 1) * 10;
            stringBuilder.append(nextMultipleOf10 - sum);
        }
    }

    int getBalance(String cardNumber) {
        try (Statement statement = dataSource.getConnection().createStatement()) {
            String sql = String.format("SELECT balance FROM card WHERE number = '%s';", cardNumber);
            ResultSet resultBalance = statement.executeQuery(sql);
            return resultBalance.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
