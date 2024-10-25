package es.upm.etsisi.models.entities;

public interface Entity {    // TODO: implement
    void add(Entity entity);

    void remove(Entity entity);

    void show();

    Entity getChild(int i);
}