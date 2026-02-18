package models;

public class Citizen {

    private String citizenId;
    private String name;
    private String email;

    public Citizen(String citizenId, String name, String email) {
        if (citizenId.length() == 16){
            this.citizenId = citizenId;}
        else{
            System.out.println("id should be 16 digits.");
        }
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
