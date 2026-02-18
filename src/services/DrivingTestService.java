package services;

public class DrivingTestService extends GovernmentService {

    private boolean approved = false;

    public DrivingTestService(String serviceId) {
        super(serviceId, "Driving Test", 10000);
    }

    @Override
    public void apply(String citizenId, double amountPaid) {

        if (amountPaid >= getFee()) {
            approved = true;
            System.out.println("Driving Test Application Submitted.");
        } else {
            System.out.println("Not enough payment.");
        }
    }

    @Override
    public void checkStatus() {

        if (approved) {
            System.out.println("Driving Test Approved.");
        } else {
            System.out.println("Driving Test Pending.");
        }
    }
}
