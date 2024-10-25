package es.upm.etsisi.models.entities;

public interface Entity {
    void add(Entity entity);

    void remove(Entity entity);

    void show();

    Entity getChild(Entity entity);
}