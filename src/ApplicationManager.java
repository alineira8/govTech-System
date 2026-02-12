import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class ApplicationManager {

    private ArrayList<ServiceApplication> applications;

    // Constructor
    public ApplicationManager() {
        applications = new ArrayList<>();
    }


    public void addApplication(ServiceApplication application) {
        applications.add(application);
    }


    public ServiceApplication findApplicationById(int id)
            throws ApplicationNotFoundException {

        for (ServiceApplication app : applications) {
            if (app.getApplicationId() == id) {
                return app;
            }
        }
        throw new ApplicationNotFoundException(
                "Application with ID " + id + " not found");
    }


    public void approveApplication(int id)
            throws ApplicationNotFoundException, InvalidStatusException {

        ServiceApplication app = findApplicationById(id);
        app.approve();
    }

    public void displayAllApplications() {

        if (applications.isEmpty()) {
            System.out.println("No applications available.");
            return;
        }

        for (ServiceApplication app : applications) {
            System.out.println(
                    "ID: " + app.getApplicationId() +
                            " | Citizen: " + app.getCitizen().getName() +
                            " | Service: " + app.getService().getServiceName() +
                            " | Fee: " + app.getService().getFee() +
                            " | Status: " + app.getStatus()
            );
        }
    }


    public ArrayList<ServiceApplication> getApplications() {
        return applications;
    }


    public void saveToFile(String filename) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filename))) {

            for (ServiceApplication app : applications) {
                writer.write(
                        app.getApplicationId() + "|" +
                                app.getCitizen().getCitizenId() + "|" +
                                app.getCitizen().getName() + "|" +
                                app.getCitizen().getEmail() + "|" +
                                app.getService().getServiceName() + "|" +
                                app.getService().getServiceId() + "|" +
                                app.getService().getFee() + "|" +
                                app.getStatus()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving applications: " + e.getMessage());
        }
    }


    public void loadFromFile(String filename) {

        applications.clear();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                int appId = Integer.parseInt(data[0]);
                String citizenId = data[1];
                String citizenName = data[2];
                String citizenEmail = data[3];
                String serviceName = data[4];
                String serviceId = data[5];
                double fee = Double.parseDouble(data[6]);
                String status = data[7];

                Citizen citizen =
                        new Citizen(citizenId, citizenName, citizenEmail);

                GovernmentService service;
                if (serviceName.equals("Birth Certificate")) {
                    service = new BirthCertificateService(serviceId);
                } else {
                    service = new DrivingTestService(serviceId);
                }

                ServiceApplication application =
                        new ServiceApplication(appId, citizen, service);

                if (status.equals("APPROVED")) {
                    application.approve();
                } else if (status.equals("REJECTED")) {
                    application.reject();
                }

                applications.add(application);
            }

        } catch (IOException e) {
            System.out.println(
                    "No saved data found. Starting with empty records.");
        } catch (Exception e) {
            System.out.println("Error loading applications: " + e.getMessage());
        }
    }


    public double calculateTotalRevenue() {

        double total = 0;

        for (ServiceApplication app : applications) {
            if (app.getStatus().equals("APPROVED")) {
                total += app.getService().getFee();
            }
        }
        return total;
    }


    public double calculateRevenueByService(String serviceName) {

        double total = 0;

        for (ServiceApplication app : applications) {
            if (app.getStatus().equals("APPROVED") &&
                    app.getService().getServiceName().equals(serviceName)) {

                total += app.getService().getFee();
            }
        }
        return total;
    }


    public void generateRevenueReport(String filename) {

        double totalRevenue = calculateTotalRevenue();
        double birthRevenue =
                calculateRevenueByService("Birth Certificate");
        double drivingRevenue =
                calculateRevenueByService("Driving Test");

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filename))) {

            writer.write("===== GOVERNMENT SERVICE REVENUE REPORT =====");
            writer.newLine();
            writer.newLine();

            writer.write("Total Revenue: " + totalRevenue + " RWF");
            writer.newLine();
            writer.newLine();

            writer.write("Revenue by Service Type:");
            writer.newLine();
            writer.write("- Birth Certificate: " +
                    birthRevenue + " RWF");
            writer.newLine();
            writer.write("- Driving Test: " +
                    drivingRevenue + " RWF");
            writer.newLine();
            writer.newLine();

            writer.write("Approved Applications:");
            writer.newLine();

            for (ServiceApplication app : applications) {
                if (app.getStatus().equals("APPROVED")) {
                    writer.write(
                            "ID: " + app.getApplicationId() +
                                    ", Citizen: " + app.getCitizen().getName() +
                                    ", Service: " +
                                    app.getService().getServiceName() +
                                    ", Fee: " + app.getService().getFee()
                    );
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Error generating revenue report: " + e.getMessage());
        }
    }
}
