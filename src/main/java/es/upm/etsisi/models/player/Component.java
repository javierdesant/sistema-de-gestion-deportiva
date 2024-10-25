package es.upm.etsisi.models.player;

public interface Component {    // TODO: implement
    void add(Component c);

    void remove(Component c);


    void show();


    Component getChild(int i);
}