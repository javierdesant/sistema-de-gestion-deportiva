package es.upm.etsisi.models.entities;

import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class EntityList extends List<Entity> {

    public EntityList() {
        super();
    }

    @Override
    public void add(Entity entity) {
        assert !this.contains(entity) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.addElement(entity);
    }

    @Override
    public void remove(Entity entity) {
        boolean removed = this.removeElement(entity);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    public void rank() {
        this.show();    // TODO
    }

    public Entity getByName(String name) {
        Entity res = null;

        Iterator<Entity> iterator = this.getElements().iterator();
        while (iterator.hasNext() && res == null) {
            Entity entity = iterator.next();
            if (entity.getName().equals(name)) {
                res = entity;
            }
        }

        return res;
    }
}
