package es.upm.etsisi.auth;

public class Administrator implements User {
    private String name;
    private String password;

    public Administrator(String name, String password) {
        this.name = name;   // TODO: validate for upm email
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public void setPassword(String oldPassword, String newPassword) {
        assert oldPassword != null;
        assert newPassword != null;
        assert oldPassword.equals(this.password);   // TODO: add message

        this.password = newPassword;
    }

    @Override
    public void setUsername(String name) {
        this.name = name;
    }
}
