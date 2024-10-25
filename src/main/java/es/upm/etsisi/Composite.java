package es.upm.etsisi;

import java.util.ArrayList;

public class Composite implements Component {
    private final ArrayList<Component> children;

    public Composite() {
        this.children = new ArrayList<>();
    }

    public void add(Component c) {
        this.children.add(c);
    }

    public void remove(Component c) {
        this.children.add(c);
    }

    public Component getChild(int i) {
        return this.children.get(i);
    }
}