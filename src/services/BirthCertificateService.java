package services;

public class BirthCertificateService extends GovernmentService {

    private boolean approved = false;

    public BirthCertificateService() {
        super("BC001", "Birth Certificate", 5000);
    }

    @Override
    public void apply(String citizenId, double amountPaid) {

        if (amountPaid >= getFee()) {
            approved = true;
            System.out.println("Application submitted successfully.");
        } else {
            System.out.println("Insufficient payment.");
        }
    }

    @Override
    public void checkStatus() {

        if (approved) {
            System.out.println("Application Approved.");
        } else {
            System.out.println("Application Pending or Not Paid.");
        }
    }
}
