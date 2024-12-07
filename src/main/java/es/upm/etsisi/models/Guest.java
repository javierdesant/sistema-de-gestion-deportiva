package es.upm.etsisi.models;

public class Guest extends User {
    public static Guest instance;

    private Guest() {
        super(null, null, Role.GUEST);
    }

    public static Guest getInstance() {
        if (instance == null) {
            instance = new Guest();
        }
        return instance;
    }
}
