package es.upm.etsisi.models.entities;

import es.upm.etsisi.utils.Message;

import java.util.ArrayList;

public class Team implements Entity {   // TODO: implement
    private final String name;
    private final ArrayList<Entity> children;

    public Team(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add(Entity entity) {
        this.children.add(entity);
    }

    @Override
    public void remove(Entity entity) {
        this.children.remove(entity);
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (Entity entity : this.children) {
            entity.show();
            Message.FOOTER.writeln();
        }
    }

    @Override
    public Entity getChild(Entity entity) {
        int index = this.children.indexOf(entity);

        if (index == -1) {
            return null;
        } else {
            return this.children.get(index);
        }
    }
}