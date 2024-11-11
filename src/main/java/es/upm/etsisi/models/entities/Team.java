package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public class Team extends Participant {
    private final ArrayList<Participant> children;

    public Team(String name, Statistics statistics, String adminName) {
        super(name, statistics, adminName);
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
    public ArrayList<Participant> getChildren() {
        return new ArrayList<>(this.children);
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

        return super.getStats();
    }
}