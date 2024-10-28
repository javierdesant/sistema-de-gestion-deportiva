package es.upm.etsisi.auth;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        assert this.isUpmEmail(username);   // TODO: add Message

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
        assert oldPassword != null;
        assert newPassword != null;
        assert oldPassword.equals(this.password);   // TODO: add message

        this.password = newPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
