package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.views.EntityView;

import java.util.ArrayList;

public abstract class Entity {
    private final EntityView view;
    private final String name;
    private Statistics stats;

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

    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    public void show() {
        this.view.display();
    }

    public abstract ArrayList<Entity> getChildren();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Entity entity = (Entity) object;
        return this.getName().equals(entity.getName());
    }
}