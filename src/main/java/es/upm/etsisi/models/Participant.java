package es.upm.etsisi.models;

import java.util.LinkedList;

public interface Participant {
    String getName();

    Statistics getStats();

    LinkedList<Player> getChildren();

    boolean hasChildren();
}
