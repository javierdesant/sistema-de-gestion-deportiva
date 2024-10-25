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
        this.children.add(entity);
    }

    @Override
    public void show() {

    }

    @Override
    public Entity getChild(int i) {
        return this.children.get(i);
    }
}