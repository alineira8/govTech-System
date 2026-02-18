package models;

import services.*;
import java.util.Scanner;

public class Application {

    private GovernmentService service;
    private Scanner scanner = new Scanner(System.in);
    private String applicationId = "APP001";

    public void apply(String citizenId) {

        if (citizenId == null || citizenId.isEmpty()) {
            System.out.println("No ID provided.");
            return;
        }

        System.out.println("Choose Service:");
        System.out.println("1. Birth Certificate");
        System.out.println("2. Driving Test");

        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                service = new BirthCertificateService();
                break;

            case 2:
                service = new DrivingTestService("DT001");
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Service Fee: " + service.getFee());
        System.out.print("Enter amount to pay: ");
        double amount = scanner.nextDouble();

        service.apply(citizenId, amount);

        System.out.println("Application ID: " + applicationId);
    }

    public void showProgress() {

        if (service == null) {
            System.out.println("No application found.");
            return;
        }

        System.out.println("Application ID: " + applicationId);
        service.checkStatus();
    }
}
