package es.upm.etsisi.models;

public class Guest extends User {
    public static final Guest instance = new Guest();

    private Guest() {
        super(null, null, Role.GUEST);
    }

    public static Guest getInstance() {
        return instance;
    }
}
