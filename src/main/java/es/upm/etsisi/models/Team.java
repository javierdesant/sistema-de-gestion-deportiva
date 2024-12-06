package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.util.HashSet;
import java.util.LinkedList;

public class Team implements Participant {
    private final String name;
    private final Administrator admin;
    private final HashSet<Player> members;

    public Team(String name, Administrator admin, Player player) {
        assert admin.getRole() == Role.ADMIN;

        this.name = name;
        this.admin = admin;
        this.members = new HashSet<>();
        this.members.add(player);
    }

    public ErrorType add(Player player) {
        boolean added = this.members.add(player);

        if (!added) {
            return ErrorType.PLAYER_ALREADY_IN_TEAM_ERROR;
        }
        return ErrorType.NULL;
    }

    public boolean remove(Player player) {
        assert this.size() > 1;
        return this.members.remove(player);
    }

    public int size() {
        return this.members.size();
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

            for (Player child : this.members) {
                product *= child.getStats().get(category);
            }

            double geometricMean = Math.pow(product, 1.0 / this.members.size());
            stats.setStatistic(category, geometricMean);
        }

        return stats;
    }

    @Override
    public LinkedList<Player> getMembers() {
        return new LinkedList<>(this.members);
    }

    @Override
    public boolean contains(Player player) {
        return this.members.contains(player);
    }

    @Override
    public String getKey() {
        return this.name;
    }

    @Override
    public boolean hasChildren() {
        assert !this.members.isEmpty();
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Team that = (Team) object;
        return this.getKey().equals(that.getKey());
    }
}
