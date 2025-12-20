package atm;

import java.util.Scanner;

public class Screen {

    public void show(String message) {
        System.out.println(message);
    }


    private boolean isNumeric(String input) {
        return input != null && input.matches("\\d+");
    }

    public String getPinInput(Scanner scanner, String message) {
        String input;

        do {
            show(message);
            input = scanner.nextLine().trim();

            if (isNumeric(input) && !input.isEmpty()) {
                return input;
            } else {
                show("Помилка: Ввід має містити лише цифри.");
            }
        } while (true);
    }

    public double getPositiveDouble(Scanner scanner, String message) {
        double result;

        do {
            show(message);

            while (!scanner.hasNextDouble()) {
                String invalidInput = scanner.next();
                show("Помилка вводу: '" + invalidInput + "' не є коректним числом. Спробуйте ще раз.");
                show(message);
            }

            result = scanner.nextDouble();
            scanner.nextLine();

            if (result > 0.0) {
                return result;
            } else {
                show("Помилка: Сума має бути більшою за нуль.");
            }

        } while (true);
    }
}
