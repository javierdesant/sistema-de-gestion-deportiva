package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public class Team extends Entity {
    private final ArrayList<Entity> children;

    public Team(String name, Statistics statistics) {
        super(name, statistics);
        this.children = new ArrayList<>();
    }

    public Team(String name) {
        this(name, new Statistics());
    }

    public void add(Entity entity) {
        this.children.add(entity);
    }

    public void remove(Entity entity) {
        this.children.remove(entity);
    }

    @Override
    public ArrayList<Entity> getChildren() {
        return new ArrayList<>(this.children);
    }

    @Override
    public Statistics getStats() {
        Statistics stats = new Statistics();

        for (Entity child : this.children) {
            for (Category category : Category.values()) {
                stats.setStatistic(category, stats.get(category) + child.getStat(category));
            }
        }

        return stats;
    }
}