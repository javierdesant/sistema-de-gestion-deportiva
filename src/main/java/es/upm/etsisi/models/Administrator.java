package es.upm.etsisi.models;

import es.upm.etsisi.utils.UpmEmail;

public class Administrator extends User {
    public Administrator(UpmEmail username, String password) {
        super(username, password, Role.ADMIN);
    }
}
