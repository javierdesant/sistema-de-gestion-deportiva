package es.upm.etsisi.service;

import es.upm.etsisi.models.auth.User;
import es.upm.etsisi.models.auth.UserList;

public class AuthController {
    private final UserList userList;
    private User user;

    public AuthController() {
        this.userList = new UserList();
        this.user = null;
    }

    public User getUser() {
        return this.user;
    }

    public boolean login(String username, String password) {
        User user = this.userList.getByUsername(username);
        if (user != null && user.validate(password)) {
            this.user = user;
            return true;
        }
        return false;
    }

    public void logout() {
        this.user = null;
    }

    public void register(String username, String password) {
//        assert this.userList.getByUsername(username) == null;   // TODO: add Message.USER_ALREADY_EXISTS
//
//        User user;
//
//        if (username.endsWith("@alumnos.upm.es")) {
////            user = this.playerProfileView.read(username, password);   // FIXME
//        } else {
////            user = this.administratorView.read(username, password);   // FIXME
//        }
//
//        assert user != null;
//        this.userList.add(user);
    }
}
