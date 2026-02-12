public class ServiceApplication {

    private int applicationId;
    private Citizen citizen;
    private GovernmentService service;
    private String status; // PENDING, APPROVED, REJECTED

    public ServiceApplication(int applicationId, Citizen citizen, GovernmentService service) {
        this.applicationId = applicationId;
        this.citizen = citizen;
        this.service = service;
        this.status = "PENDING";
    }

    public int getApplicationId() {
        return applicationId;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public GovernmentService getService() {
        return service;
    }

    public String getStatus() {
        return status;
    }

    public void approve() throws InvalidStatusException {
        if (status.equals("APPROVED")) {
            throw new InvalidStatusException("Application already approved");
        }
        status = "APPROVED";
    }

    public void reject() {
        status = "REJECTED";
    }
}
