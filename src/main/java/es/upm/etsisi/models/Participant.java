package es.upm.etsisi.models;

public interface Participant {
    String getName();

    Statistics getStats();

    void setStats(Statistics stats);

    ParticipantList getChildren();

    boolean hasChildren();
}
