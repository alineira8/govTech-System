package services;

public abstract class GovernmentService {

    private String serviceId;
    private String serviceName;
    private double fee;

    public GovernmentService(String serviceId, String serviceName, double fee) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.fee = fee;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getFee() {
        return fee;
    }

    public abstract void apply(String citizenId, double amountPaid);

    public abstract void checkStatus();
}
