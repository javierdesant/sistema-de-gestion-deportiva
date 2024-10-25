package es.upm.etsisi.models.player;

import es.upm.etsisi.auth.User;

public class Leaf implements Component, User {  // TODO: implement


    @Override
    public void add(Component c) {
    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public void show() {

    }

    public Component getChild(int i) {
        return null;
    }
}