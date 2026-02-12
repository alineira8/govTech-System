import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();

        // Load saved applications
        manager.loadFromFile("applications.txt");

        int choice;

        do {
            System.out.println("\n===== DIGITAL GOVTECH SYSTEM =====");
            System.out.println("1. Add new application");
            System.out.println("2. Approve application");
            System.out.println("3. View all applications");
            System.out.println("4. View total revenue");
            System.out.println("5. Generate revenue report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            try {
                switch (choice) {

                    case 1:
                        // Add application
                        System.out.print("Enter Application ID (number): ");
                        int appId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Citizen ID: ");
                        String citizenId = scanner.nextLine();

                        System.out.print("Enter Citizen Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Citizen Email: ");
                        String email = scanner.nextLine();

                        Citizen citizen =
                                new Citizen(citizenId, name, email);

                        System.out.println("Select Service:");
                        System.out.println("1. Birth Certificate");
                        System.out.println("2. Driving Test");
                        System.out.print("Choice: ");
                        int serviceChoice = scanner.nextInt();
                        scanner.nextLine();

                        GovernmentService service;
                        if (serviceChoice == 1) {
                            service = new BirthCertificateService("BC-" + appId);
                        } else {
                            service = new DrivingTestService("DT-" + appId);
                        }

                        ServiceApplication application =
                                new ServiceApplication(appId, citizen, service);

                        manager.addApplication(application);
                        System.out.println("Application added successfully.");
                        break;

                    case 2:
                        // Approve application
                        System.out.print("Enter Application ID to approve: ");
                        int approveId = scanner.nextInt();
                        scanner.nextLine();

                        manager.approveApplication(approveId);
                        System.out.println("Application approved.");
                        break;

                    case 3:
                        // View all applications
                        manager.displayAllApplications();
                        break;

                    case 4:
                        // View total revenue
                        System.out.println(
                                "Total Revenue: " +
                                        manager.calculateTotalRevenue() +
                                        " RWF");
                        break;

                    case 5:
                        // Generate revenue report
                        manager.generateRevenueReport("revenue_report.txt");
                        System.out.println("Revenue report generated.");
                        break;

                    case 0:
                        // Exit
                        manager.saveToFile("applications.txt");
                        manager.generateRevenueReport("revenue_report.txt");
                        System.out.println("Data saved. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (ApplicationNotFoundException |
                     InvalidStatusException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 0);

        scanner.close();
    }
}
