import models.Application;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Application application = new Application();

        System.out.print("Enter Citizen ID: ");
        String citizenId = scanner.nextLine();

        application.apply(citizenId);

        System.out.println("\nChecking Application Status...");
        application.showProgress();
    }
}
