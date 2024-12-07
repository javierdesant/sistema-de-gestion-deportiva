package es.upm.etsisi.models;

import java.util.LinkedList;

public interface Participant {
    String getName();

    Statistics getStats();

    LinkedList<Player> getMembers();

    boolean hasChildren();

    boolean contains(Player player);

    Object getKey();
}
