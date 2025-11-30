import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Screen screen = new Screen();

        Bankomat bankomat = new Bankomat(screen, scanner);
        bankomat.start();

        scanner.close();
    }
}