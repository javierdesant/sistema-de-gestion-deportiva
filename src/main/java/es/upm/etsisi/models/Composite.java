package es.upm.etsisi.models;

import java.util.ArrayList;

public class Composite implements Component {
    private final ArrayList<Component> children;

    public Composite() {
        this.children = new ArrayList<>();
    }

    @Override
    public void add(Component c) {
        this.children.add(c);
    }

    @Override
    public void remove(Component c) {
        this.children.add(c);
    }

    @Override
    public void show() {

    }

    @Override
    public Component getChild(int i) {
        return this.children.get(i);
    }
}