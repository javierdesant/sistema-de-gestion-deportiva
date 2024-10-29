package es.upm.etsisi.models.entities;

import java.util.ArrayList;

public interface Entity {
    String getName();

    void add(Entity entity);
    void remove(Entity entity);

    ArrayList<Entity> getChildren();
}