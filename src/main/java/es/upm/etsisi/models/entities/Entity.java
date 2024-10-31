package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.views.EntityView;

import java.util.ArrayList;

public abstract class Entity {
    private final EntityView view;
    private final String name;
    private final Statistics stats;

    public Entity(String name, Statistics stats) {
        this.view = new EntityView(this);
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return this.name;
    }

    public Statistics getStats() {
        return this.stats;
    }

    public double getStat(Category category) {
        return this.stats.get(category);
    }

    // TODO: setStats

    public void show() {
        this.view.display();
    }

    public abstract ArrayList<Entity> getChildren();    // should this be public ?
}