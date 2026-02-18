package model;

public class Citizen {

    private String citizenId;
    private String name;
    private String email;

    public Citizen(String citizenId, String name, String email) {
        this.citizenId = citizenId;
        this.name = name;
        this.email = email;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
