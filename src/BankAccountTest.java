import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount account;
    private final String correctPin = "1234";
    private final double initialBalance = 1000.00;

    @BeforeEach
     void setUp() {
        account = new BankAccount(correctPin, initialBalance);
    }

    @Test
    void checkPin_CorrectPin_ReturnsTrue() {
        boolean result = account.checkPin(correctPin);

        assertTrue(result, "Має повернути true для коректного PIN");
    }

    @Test
    void checkPin_IncorrectPin_ReturnsFalse() {
        boolean result = account.checkPin("4321");

        assertFalse(result, "Має повернути false для некоректного PIN");
    }

    @Test
    void getBalance_InitialBalance_ReturnsCorrectAmount() {
        double balance = account.getBalance();

        assertEquals(initialBalance, balance, 0.001,
                "Має повернути початковий баланс");
    }

    @Test
    void deposit_PositiveAmount_UpdatesBalance() {
        double depositAmount = 250.50;
        double expectedBalance = initialBalance + depositAmount;

        account.deposit(depositAmount);

        assertEquals(expectedBalance, account.getBalance(), 0.001,
                "Баланс має збільшитися на суму поповнення");
    }

    @Test
    void withdraw_SufficientFunds_ReturnsTrueAndUpdatesBalance() {
        double withdrawAmount = 300.00;
        double expectedBalance = initialBalance - withdrawAmount;

        boolean result = account.withdraw(withdrawAmount);

        assertTrue(result, "Має повернути true, оскільки коштів достатньо");
        assertEquals(expectedBalance, account.getBalance(), 0.001,
                "Баланс має зменшитися на суму зняття");
    }

    @Test
    void withdraw_InsufficientFunds_ReturnsFalseAndBalanceUnchanged() {
        double withdrawAmount = 1500.00;

        boolean result = account.withdraw(withdrawAmount);

        assertFalse(result, "Має повернути false, оскільки коштів недостатньо");
        assertEquals(initialBalance, account.getBalance(), 0.001,
                "Баланс має залишитися незмінним");
    }

    @Test
    void withdraw_AllFunds_ReturnsTrueAndBalanceIsZero() {
        boolean result = account.withdraw(initialBalance);

        assertTrue(result, "Має повернути true при знятті всього балансу");
        assertEquals(0.00, account.getBalance(), 0.001,
                "Баланс має стати нульовим");
    }

}