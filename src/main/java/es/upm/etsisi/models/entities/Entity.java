package es.upm.etsisi.models.entities;

public interface Entity {
    String getName();

    void add(Entity entity);

    void remove(Entity entity);

    void show();

    Entity getChild(Entity entity);
}