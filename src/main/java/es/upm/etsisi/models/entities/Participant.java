package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public interface Participant {
    String getName();
    Statistics getStats();
    void setStats(Statistics stats);
    ArrayList<Participant> getChildren();
}
