package es.upm.etsisi.auth;

import es.upm.etsisi.utils.DNI;

public class PlayerProfile extends User {
    private final String firstName;
    private final String lastName;
    private final DNI dni;

    public PlayerProfile(String username, String password, String dni, String firstName, String lastName) {
        super(username, password);
        this.dni = new DNI(dni);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getDni() {
        return this.dni.getValue();
    }

    public void writeln() {
        System.out.println(firstName + " " + lastName);
    }
}
