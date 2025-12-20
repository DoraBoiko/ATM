package commands;
import atm.BankAccount;
import atm.Screen;

public class CheckBalanceCommand implements Command {

    private BankAccount account;
    private Screen screen;

    public CheckBalanceCommand(BankAccount account, Screen screen) {
        this.account = account;
        this.screen = screen;
    }

    @Override
    public void execute() {
        screen.show("Баланс: " + account.getBalance() + " грн");
    }
}
