package es.upm.etsisi.auth;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        assert username != null;
        assert password != null;
        this.username = username;   // TODO: validate for upm email
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String oldPassword, String newPassword) {
        assert oldPassword != null;
        assert newPassword != null;
        assert oldPassword.equals(this.password);   // TODO: add message

        this.password = newPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
