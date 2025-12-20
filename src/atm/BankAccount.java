package atm;

public class BankAccount {
    private final String pin;
    private double balance;

    public BankAccount(String pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public boolean checkPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
