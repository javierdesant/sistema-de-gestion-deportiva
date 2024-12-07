package es.upm.etsisi.models;

import es.upm.etsisi.utils.UpmEmail;

public abstract class User {
    private final UpmEmail username;
    private final String password;
    private final Role role;

    public User(UpmEmail username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public boolean isLoggedIn() {
        return this.role != Role.GUEST;
    }

    public String getUsername() {
        return this.username.toString();
    }

    public boolean validate(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        User that = (User) object;
        return this.getUsername().equals(that.getUsername());
    }

    @Override
    public String toString() {
        String username = this.username.toString();
        return username.split("@")[0];
    }
}
