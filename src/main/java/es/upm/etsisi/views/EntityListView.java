package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

public class EntityListView implements ModelView {
    private final EntityList entityList;

    public EntityListView(EntityList entityList) {
        this.entityList = entityList;
    }

    @Override
    public void display() {
        if (this.entityList.isEmpty()) {
            Message.NO_PLAYERS.writeln();   // TODO: replace with NO_MEMBERS Message
        } else {
            // TODO: add header Message
            for (Entity entity : this.entityList.getElements()) {
                entity.show();
                Message.LIGHT_FOOTER.writeln();
            }
            Message.FOOTER.writeln();
        }
    }
}
