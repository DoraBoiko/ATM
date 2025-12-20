package atm;

import java.util.Scanner;
import commands.Command;
import commands.CheckBalanceCommand;
import operations.OperationTemplate;
import operations.WithdrawOperation;
import security.PinValidationStrategy;
import security.SimplePinValidation;

public class Bankomat {

    private BankAccount account;
    private Screen screen;
    private Scanner scanner;
    private PinValidationStrategy pinValidator;

    public Bankomat(Screen screen, Scanner scanner) {
        this.screen = screen;
        this.scanner = scanner;
        this.pinValidator = new SimplePinValidation();

        String pin = screen.getPinInput(scanner, "Створіть PIN:");
        double balance = screen.getPositiveDouble(scanner, "Введіть початковий баланс:");
        this.account = new BankAccount(pin, balance);
    }

    public void start() {
        final int maxAttempts = 3;
        int attempts = 0;
        boolean authorized = false;

        while (attempts < maxAttempts && !authorized) {
            screen.show("Введіть PIN:");
            String input = scanner.nextLine();

            if (pinValidator.validate(input, account.getPin())) {
                authorized = true;
            } else {
                attempts++;
                if (attempts < maxAttempts) {
                    screen.show("Неправильний PIN. Спробуйте ще раз (" + (maxAttempts - attempts) + " спроби залишилось).");
                } else {
                    screen.show("Ви ввели неправильний PIN 3 рази. Доступ заблоковано.");
                    return;
                }
            }
        }

        boolean run = true;
        while (run) {
            screen.show("\n1 – Переглянути баланс");
            screen.show("2 – Зняти готівку");
            screen.show("3 – Поповнити банківський рахунок");
            screen.show("4 – Вийти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Command checkBalance = new CheckBalanceCommand(account, screen);
                    checkBalance.execute();
                    break;
                case "2":
                    OperationTemplate withdraw = new WithdrawOperation(account, screen, scanner);
                    withdraw.perform();
                    break;
                case "3":
                    deposit();
                    break;
                case "4":
                    run = false;
                    screen.show("Дякуємо за використання!");
                    break;
                default:
                    screen.show("Некоректний вибір.");
            }
        }
    }

    private void deposit() {
        double sum = screen.getPositiveDouble(scanner, "Введіть суму:");
        account.deposit(sum);
        screen.show("Поповнено на " + sum + " грн");
    }
}
