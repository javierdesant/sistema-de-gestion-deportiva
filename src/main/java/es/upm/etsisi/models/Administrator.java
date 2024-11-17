package es.upm.etsisi.models;

public class Administrator extends User {
    public Administrator(String username, String password) {
        super(username, password, Role.ADMIN);
    }
}
