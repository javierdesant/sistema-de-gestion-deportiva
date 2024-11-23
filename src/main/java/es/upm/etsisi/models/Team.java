package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.util.HashSet;
import java.util.LinkedList;

public class Team implements Participant {
    private final String name;
    private final Administrator admin;
    private final HashSet<Player> children;

    public Team(String name, Administrator admin, Player player) {
        assert admin.getRole() == Role.ADMIN;

        this.name = name;
        this.admin = admin;
        this.children = new HashSet<>();
        this.children.add(player);
    }

    public Error add(Player player) {
        boolean added = this.children.add(player);

        if (!added) {
            return Error.PLAYER_ALREADY_IN_TEAM_ERROR;
        }
        return null;
    }

    public boolean remove(Player player) {
        return this.children.remove(player);
    }

    public int size() {
        return this.children.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Statistics getStats() {
        Statistics stats = new Statistics();

        for (Category category : Category.values()) {
            double product = 1.0;

            for (Player child : this.children) {
                product *= child.getStats().get(category);
            }

            double geometricMean = Math.pow(product, 1.0 / this.children.size());
            stats.setStatistic(category, geometricMean);
        }

        return stats;
    }

    @Override
    public LinkedList<Player> getChildren() {
        return new LinkedList<>(this.children);
    }

    @Override
    public boolean contains(Player player) {
        return this.children.contains(player);
    }

    @Override
    public boolean hasChildren() {
        assert !this.children.isEmpty();
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
