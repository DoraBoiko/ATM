package operations;

import atm.BankAccount;
import atm.Screen;
import java.util.Scanner;

public class WithdrawOperation extends OperationTemplate {

    private BankAccount account;
    private Screen screen;
    private Scanner scanner;

    public WithdrawOperation(BankAccount account, Screen screen, Scanner scanner) {
        this.account = account;
        this.screen = screen;
        this.scanner = scanner;
    }

    @Override
    protected void executeOperation() {
        double amount = screen.getPositiveDouble(scanner, "Введіть суму:");

        if (account.withdraw(amount)) {
            screen.show("Знято " + amount + " грн");
        } else {
            screen.show("Недостатньо коштів на рахунку!");
        }
    }
}
