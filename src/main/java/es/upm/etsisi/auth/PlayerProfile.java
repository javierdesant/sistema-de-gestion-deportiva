package es.upm.etsisi.auth;

import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.utils.DNI;

public class PlayerProfile extends User {
    private final String firstName;
    private final String lastName;
    private final DNI dni;
    private final Player player;

    public PlayerProfile(String username, String password, DNI dni, String firstName, String lastName, Player player) {
        super(username, password);
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.player = player;
    }

    public String getDni() {
        return this.dni.getValue();
    }

    public Statistics getStats() {
        return this.player.getStats();
    }

    public void show() {
        System.out.println(firstName + " " + lastName);
    }
}
