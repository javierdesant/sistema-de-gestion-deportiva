package es.upm.etsisi.models;

public enum Role {
    ADMIN,
    PLAYER,

    GUEST;

    public boolean isGuest() {
        return this == GUEST;
    }
}
