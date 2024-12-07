package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.util.LinkedList;

public class Team implements Participant {
    private final String name;
    private final ParticipantList members;
    private final Administrator admin;

    public Team(String name, ParticipantList members, Administrator admin) {
        this.name = name;
        this.members = members;
        this.admin = admin;
    }

    public ErrorType add(Player player) {
        ErrorType error = this.members.add(player);

        if (error == ErrorType.DUPLICATE_PLAYER_ERROR) {
            return ErrorType.PLAYER_ALREADY_IN_TEAM_ERROR;
        } else {
            assert error.isNull();
        }
        return error;
    }

    public boolean remove(Player player) {
        assert this.size() > 2;
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

            for (Participant member : this.members.getElements()) {
                product *= member.getStats().get(category);
            }

            double geometricMean = Math.pow(product, 1.0 / this.members.size());
            stats.setStatistic(category, geometricMean);
        }

        return stats;
    }

    @Override
    public LinkedList<Player> getMembers() {
        LinkedList<Player> members = new LinkedList<>();

        for (Participant member : this.members.getElements()) {
            assert !member.hasChildren();
            members.add((Player) member);
        }

        return members;
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
