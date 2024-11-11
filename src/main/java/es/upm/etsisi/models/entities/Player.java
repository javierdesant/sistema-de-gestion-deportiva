package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.auth.Role;
import es.upm.etsisi.models.auth.User;
import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.utils.DNI;

import java.util.ArrayList;

public class Player extends User implements Participant {
    private final String firstName;
    private final String lastName;
    private final DNI dni;
    private Statistics stats;
    private final String adminName;

    public Player(String username,
                  String password,
                  String firstName,
                  String lastName,
                  DNI dni,
                  Statistics statistics,
                  User admin) {

        super(username, password, Role.PLAYER);

        assert admin.getRole() == Role.ADMIN;

        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.stats = statistics;
        this.adminName = admin.getUsername();
    }

    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public DNI getDni() {
        return this.dni;
    }

    @Override
    public Statistics getStats() {
        return this.stats;
    }

    @Override
    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    @Override
    public ArrayList<Participant> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}