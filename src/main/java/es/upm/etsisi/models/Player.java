package es.upm.etsisi.models;

import es.upm.etsisi.utils.UpmEmail;

import java.util.LinkedList;

public class Player extends User implements Participant {
    private final String firstName;
    private final String lastName;
    private final DNI dni;
    private final Administrator admin;
    private Statistics stats;

    public Player(UpmEmail username,
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
        this.admin = (Administrator) admin;
    }

    public Player(UpmEmail username,
                  String password,
                  String firstName,
                  String lastName,
                  DNI dni,
                  User admin) {
        this(username, password, firstName, lastName, dni, new Statistics(), admin);
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

    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    @Override
    public boolean contains(Player player) {
        return false;
    }

    @Override
    public LinkedList<Player> getChildren() {
        return new LinkedList<>();
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Player player = (Player) object;
        return this.dni.equals(player.dni);
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }
}