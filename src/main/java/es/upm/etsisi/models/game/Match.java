package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

import java.util.*;

public class Match {
    private final ArrayList<Entity> entities;

    public Match(EntityList entityList, Collection<Entity> entities) {
        assert entities != null;
        assert entities.size() >= 2;                    // TODO: update messages
        assert entityList.containsAll(entities);
        for (Entity entity : entities) {
            assert Collections.frequency(entities, entity) == 1;    // FIXME: for break ?
        }                                                            // TODO: improve...

        this.entities = new ArrayList<>(entities);
    }

    public Match(EntityList entityList, Entity... entities) {
        this(entityList, Arrays.asList(entities));  // FIXME: maybe is null ?
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public Entity getEntity(int index) {
        assert 0 <= index && index < this.entities.size() : Message.INVALID_INDEX;
        return this.entities.get(index);
    }

    public void show() {
        Iterator<Entity> iterator = this.entities.iterator();

        iterator.next().show();
        do {
            System.out.println("vs");
            iterator.next().show();
        } while (iterator.hasNext());
    }

    public boolean contains(Entity entity) {
        return this.entities.contains(entity);
    }

    public boolean contains(String entityName) {
        boolean found = false;

        Iterator<Entity> iterator = this.entities.iterator();
        while (iterator.hasNext() && !found) {
            found = entityName.equals(iterator.next().getName());
        }

        return found;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Match match = (Match) object;
        ArrayList<Entity> entities = new ArrayList<>(match.getEntities());

        if (this.entities.size() != entities.size()) {
            return false;
        }

        for (Entity entity : this.entities) {
            entities.remove(entity);
        }

        return entities.isEmpty();
    }
}
