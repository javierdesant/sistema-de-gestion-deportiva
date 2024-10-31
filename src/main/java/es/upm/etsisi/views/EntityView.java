package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Entity;

public class EntityView implements View {
    private final Entity entity;

    public EntityView(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void display() {
        System.out.println(this.entity.getName());
        this.entity.getStats().show();
        for (Entity child : this.entity.getChildren()) {
            child.show();
        }
    }
}
