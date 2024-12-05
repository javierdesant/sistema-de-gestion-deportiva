package es.upm.etsisi.service;

import es.upm.etsisi.models.User;

public interface Authenticator {
    ErrorType login(String username, String password);

    void logout();

    User getUser();
}
