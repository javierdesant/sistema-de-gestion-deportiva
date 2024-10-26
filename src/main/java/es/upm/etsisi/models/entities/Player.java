package es.upm.etsisi.models.entities;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.models.game.Categories;
import es.upm.etsisi.models.game.Statistics;

public class Player implements Entity {
    private final String name;
    private final String adminName;
    private final Statistics statistics;

    public Player(String name, String adminName, Statistics statistics) {
        this.name = name;
        this.adminName = adminName;
        this.statistics = statistics;
    }

    public Player(String name, String adminName) {
        this(name, adminName, new Statistics());
    }

    public Player(String name, Administrator administrator) {
        this(name, administrator.getUsername());
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setStatistic(Categories category, double value) {
        this.statistics.setStatistic(category, value);
    }

    public double getStatistic(Categories category) {
        return this.statistics.getStatistic(category);
    }

    public Statistics getAllStatistics() {
        return this.statistics;
    }

    @Override
    public void show() {
        System.out.println("Jugador: " + this.name);
        System.out.println("Estadísticas: ");
        this.statistics.writeln();
    }

    public void showPlayer() {
        System.out.println("Jugador: " + this.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return this.name.equals(player.getName());
    }

    @Override
    public void add(Entity entity) {
    }

    @Override
    public void remove(Entity entity) {
    }

    @Override
    public Entity getChild(Entity entity) {
        return null;
    }
}