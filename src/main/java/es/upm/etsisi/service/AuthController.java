package es.upm.etsisi.service;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.auth.UserList;
import es.upm.etsisi.views.AdministratorView;
import es.upm.etsisi.views.PlayerProfileView;

public class AuthController {
    private final UserList userList;
    private final PlayerProfileView playerProfileView;
    private final AdministratorView administratorView;
    private User user;

    public AuthController() {
        this.user = null;
        this.userList = new UserList();
        this.playerProfileView = new PlayerProfileView();
        this.administratorView = new AdministratorView();
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
        assert this.userList.getByUsername(username) == null;   // TODO: add Message.USER_ALREADY_EXISTS

        User user;

        if (username.endsWith("@alumnos.upm.es")) {
            user = this.playerProfileView.read(username, password);
        } else {
            user = this.administratorView.read(username, password);
        }

        assert user != null;
        this.userList.add(user);
    }
}
