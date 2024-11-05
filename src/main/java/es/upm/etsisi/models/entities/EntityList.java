package es.upm.etsisi.models.entities;

import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.EntityListView;

public class EntityList extends List<Entity> {
    private final EntityListView view;

    public EntityList() {
        super();
        this.view = new EntityListView(this);
    }

    @Override
    public void add(Entity entity) {
        assert !this.contains(entity) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.addElement(entity);
    }

    @Override
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    public void remove(Entity entity) {
        boolean removed = this.removeElement(entity);
    }

    @Override
    public void show() {
        this.view.display();
    }

    public void rank() {
        this.show();    // TODO
    }
}
