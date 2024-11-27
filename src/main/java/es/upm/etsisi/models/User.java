package es.upm.etsisi.models;

public abstract class User {
    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, Role role) {
        assert isUpmEmail(username);
        assert role != null;

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public static boolean isUpmEmail(String email) {
        return email.contains("@") && email.endsWith("upm.es");
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
