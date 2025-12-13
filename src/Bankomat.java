import java.util.Scanner;

public class Bankomat {

    private final BankAccount account;
    private final Screen screen;
    private final Scanner scanner;

    public Bankomat(Screen screen, Scanner scanner) {
        this.screen = screen;
        this.scanner = scanner;

        String pin = screen.getPinInput(scanner, "Створіть PIN:");
        double balance = screen.getPositiveDouble(scanner, "Введіть початковий баланс:");

        this.account = new BankAccount(pin, balance);
    }

    public void start() {
        screen.show("Введіть PIN:");
        String input = scanner.nextLine();

        if (!account.checkPin(input)) {
            screen.show("Неправильний PIN");
            return;
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
                    showBalance();
                    break;
                case "2":
                    withdraw();
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

    private void showBalance() {
        screen.show("Ваш баланс: " + account.getBalance() + " грн");
    }

    private void deposit() {
        double sum = screen.getPositiveDouble(scanner, "Введіть суму:");
        account.deposit(sum);
        screen.show("Поповнено на " + sum + " грн");
    }

    private void withdraw() {
        double sum = screen.getPositiveDouble(scanner, "Сума зняття:");
        if (account.withdraw(sum)) {
            screen.show("Знято " + sum + " грн");
        } else {
            screen.show("Недостатньо коштів!");
        }
    }
}
