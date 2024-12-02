package es.upm.etsisi.service;

import es.upm.etsisi.models.User;
import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.models.Guest;
import es.upm.etsisi.models.UserList;
import es.upm.etsisi.utils.UpmEmail;

public class AuthenticationService implements Authenticator {
    private final UserList userList;
    private User user;

    public AuthenticationService() {
        this.user = Guest.getInstance();
        this.userList = new UserList(new Administrator(UpmEmail.valueOf("admin@upm.es"), "admin"));
    }

    public ErrorType login(String username, String password) {
        ErrorType error;

        User user = this.userList.findByUsername(username);
        if (user != null && user.validate(password)) {
            this.user = user;
            error = ErrorType.NULL;
        } else if (user == null) {
            error = ErrorType.USER_NOT_FOUND;
        } else {
            error = ErrorType.WRONG_PASSWORD;
        }

        return error;
    }

    public ErrorType signIn(User user) {
        ErrorType error;
        error = this.userList.add(user);
        assert error.isNull();
        return error;
    }

    public void logout() {
        this.user = Guest.getInstance();
    }

    public User getUser() {
        return this.user;
    }

    public UserList getUsers() {
        return this.userList;
    }

}