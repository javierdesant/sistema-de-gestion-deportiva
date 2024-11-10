package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public abstract class Participant {
    private final String name;
    private Statistics stats;
    private String adminName;

    public Participant(String name, Statistics stats, String adminName) {
        this.name = name;
        this.stats = stats;
        this.adminName = adminName;
    }

    public String getName() {
        return this.name;
    }

    public Statistics getStats() {
        return this.stats;
    }

    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    public abstract ArrayList<Participant> getChildren();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Participant participant = (Participant) object;
        return this.getName().equals(participant.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}