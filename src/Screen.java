import java.util.Scanner;

public class Screen {

    public void show(String message) {
        System.out.println(message);
    }

    public String getInput(Scanner scanner, String message) {
        System.out.print(message + " ");
        return scanner.nextLine();
    }

    public double getDouble(Scanner scanner, String message) {
        System.out.print(message + " ");
        return Double.parseDouble(scanner.nextLine());
    }
}