package es.upm.etsisi;

public interface Component {
    void add(Component c);

    void remove(Component c);

    Component getChild(int i);
}