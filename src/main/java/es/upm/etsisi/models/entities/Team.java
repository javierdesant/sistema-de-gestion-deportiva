package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public class Team implements Participant {
    private final String name;
    private final String adminName;
    private final ArrayList<Participant> children;
    private Statistics stats;

    public Team(String name, Statistics statistics, String adminName) {
        this.name = name;
        this.stats = statistics;
        this.adminName = adminName;
        this.children = new ArrayList<>();
    }

    public Team(String name, String adminName) {
        this(name, new Statistics(), adminName);
    }

    public void add(Participant participant) {
        this.children.add(participant);
    }

    public void remove(Participant participant) {
        this.children.remove(participant);
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

            for (Participant child : this.children) {
                product *= child.getStats().get(category);
            }

            double geometricMean = Math.pow(product, 1.0 / this.children.size());
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
    public ArrayList<Participant> getChildren() {
        return new ArrayList<>(this.children);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
