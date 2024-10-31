package es.upm.etsisi.auth;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        assert this.isUpmEmail(username) : "Not valid email";   // TODO: add Message

        this.username = username;
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String oldPassword, String newPassword) {
        assert password != null;
        assert !this.password.isBlank();

        assert this.validate(oldPassword) : "Wrong password";  // TODO: add Message

        this.password = newPassword;
    }

    public void setPassword(String password) {
        assert password != null;
        assert this.password.isBlank();

        this.password = password;
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
