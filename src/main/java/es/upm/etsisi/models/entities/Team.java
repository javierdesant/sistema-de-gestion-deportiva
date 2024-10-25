package es.upm.etsisi.models.entities;

import java.util.ArrayList;

public class Team implements Entity {   // TODO: implement
    private final ArrayList<Entity> children;

    public Team() {
        this.children = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        this.children.add(entity);
    }

    @Override
    public void remove(Entity entity) {
        this.children.remove(entity);
    }

    @Override
    public void show() {
        for (Entity entity : this.children) {
            entity.show();
        }
    }

    @Override
    public Entity getChild(Entity entity) {
        int index = this.children.indexOf(entity);

        if (index == -1) {
            return null;
        } else {
            return this.children.get(index);
        }
    }
}