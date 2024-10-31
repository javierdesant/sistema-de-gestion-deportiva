package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.MatchView;

import java.util.*;

public class Match {
    private final MatchView view;
    private final ArrayList<Entity> entities;

    public Match(EntityList entityList, Collection<Entity> entities) {
        this.view = new MatchView(this);

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
        this.view.display();
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
