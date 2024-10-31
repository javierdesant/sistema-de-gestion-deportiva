package es.upm.etsisi.models.entities;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;
import java.util.EnumMap;

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

    public EnumMap<Category, Double> getAllStatistics() {
        return this.statistics.getAllStatistics();
    }

    public double getStatistic(Category category) {
        return this.statistics.getStatistic(category);
    }

    public void setStatistic(Category category, double value) {
        this.statistics.setStatistic(category, value);
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
    public ArrayList<Entity> getChildren() {
        return new ArrayList<>();
    }
}