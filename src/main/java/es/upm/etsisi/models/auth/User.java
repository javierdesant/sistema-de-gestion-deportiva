package es.upm.etsisi.models.auth;

public abstract class User {
    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, Role role) {
        assert this.isUpmEmail(username) : "Not valid email";   // TODO: add Message, replace with an exception
        assert role != null;

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    private boolean isUpmEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        return email.endsWith("upm.es");
    }

    public String getUsername() {
        return this.username;
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
        User user = (User) object;
        return this.username.equals(user.username);
    }

    @Override
    public String toString() {
        return this.username.split("@")[0];
    }
}
