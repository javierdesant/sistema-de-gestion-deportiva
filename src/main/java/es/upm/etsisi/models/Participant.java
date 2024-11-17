package es.upm.etsisi.models;

import java.util.ArrayList;

public interface Participant {
    String getName();

    Statistics getStats();

    void setStats(Statistics stats);

    ArrayList<Participant> getChildren();
}
