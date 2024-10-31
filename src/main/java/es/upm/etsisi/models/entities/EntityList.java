package es.upm.etsisi.models.entities;

import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;

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
    public void remove(Entity player) {
        boolean removed = this.removeElement(player);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    @Override
    public void show() {
        if (this.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            for (Entity entity : this.getElements()) {
                entity.show();
                Message.FOOTER.writeln();
            }
        }
    }

    public void rank() {
        this.show();    // TODO
    }
}
