package es.upm.etsisi.auth;

public interface User {
    String getUsername();
    void setUsername(String name);
    void setPassword(String oldPassword, String newPassword);
}
