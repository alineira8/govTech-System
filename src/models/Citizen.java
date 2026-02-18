package models;

public class Citizen {

    private String citizenId;
    private String name;
    private String email;

    public Citizen(String citizenId, String name, String email) {

        if (citizenId == null || !citizenId.matches("\\d{16}")) {
            throw new IllegalArgumentException("Citizen ID must be exactly 16 digits.");
        }

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
