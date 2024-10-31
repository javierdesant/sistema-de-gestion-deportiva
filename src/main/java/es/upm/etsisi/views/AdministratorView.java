package es.upm.etsisi.views;

import es.upm.etsisi.auth.Administrator;

public class AdministratorView {

    public AdministratorView() {
    }

    public Administrator read(String username, String password) {
        return new Administrator(username, password);
    }
}