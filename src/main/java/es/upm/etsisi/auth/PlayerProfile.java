package es.upm.etsisi.auth;

import es.upm.etsisi.utils.DNI;

public class PlayerProfile implements User {
    private String userName;
    private String firstName;
    private String lastName;
    private DNI dni;
    private String password;

    public PlayerProfile(String userName, String firstName, String lastName, String dni, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = new DNI(dni);
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public void setUsername(String name) {
        this.userName = name;
    }

    @Override
    public void setPassword(String oldPassword, String newPassword) {
        assert oldPassword != null;
        assert newPassword != null;
        assert oldPassword.equals(this.password);   // TODO: add message

        this.password = newPassword;
    }

    public void writeln() {
        System.out.println(firstName + " " + lastName);
    }
}
