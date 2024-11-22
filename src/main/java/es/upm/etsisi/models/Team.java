package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.util.LinkedList;

public class Team implements Participant {
    private final String name;
    private final String adminName;
    private final ParticipantList children;
    private Statistics stats;

    public Team(String name, Statistics statistics, String adminName) {
        this.name = name;
        this.stats = statistics;
        this.adminName = adminName;
        this.children = new ParticipantList();
    }

    public Team(String name, String adminName) {
        this(name, new Statistics(), adminName);
    }

    public Error add(Participant participant) {
        return this.children.add(participant);
    }

    public boolean remove(Participant participant) {
        return this.children.remove(participant);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Statistics getStats() {
        Statistics stats = new Statistics();
        LinkedList<Participant> children = this.children.getElements();

        for (Category category : Category.values()) {
            double product = 1.0;

            for (Participant child : children) {
                product *= child.getStats().get(category);
            }

            double geometricMean = Math.pow(product, 1.0 / children.size());
            stats.setStatistic(category, geometricMean);
        }

        this.setStats(stats);
        return this.stats;
    }

    @Override
    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    @Override
    public ParticipantList getChildren() {
        return new ParticipantList(this.children);
    }

    @Override
    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
