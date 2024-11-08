package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

import java.util.*;

public class Match {
    private final ArrayList<Participant> entities;

    public Match(ParticipantList participantList, Collection<Participant> entities) {
        assert entities != null;
        assert entities.size() >= 2;                    // TODO: update messages
        assert participantList.containsAll(entities);
        for (Participant participant : entities) {
            assert Collections.frequency(entities, participant) == 1;    // FIXME: for break ?
        }                                                            // TODO: improve...

        this.entities = new ArrayList<>(entities);
    }

    public Match(ParticipantList participantList, Participant... entities) {
        this(participantList, Arrays.asList(entities));  // FIXME: maybe is null ?
    }

    public ArrayList<Participant> getEntities() {
        return new ArrayList<>(this.entities);
    }

    public Participant getEntity(int index) {
        assert 0 <= index && index < this.entities.size() : Message.INVALID_INDEX;
        return this.entities.get(index);
    }

    public boolean contains(Participant participant) {
        return this.entities.contains(participant);
    }

    public boolean contains(String entityName) {
        boolean found = false;

        Iterator<Participant> iterator = this.entities.iterator();
        while (iterator.hasNext() && !found) {
            found = entityName.equals(iterator.next().getName());
        }

        return found;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Match match = (Match) object;
        ArrayList<Participant> entities = new ArrayList<>(match.getEntities());

        if (this.entities.size() != entities.size()) {
            return false;
        }

        for (Participant participant : this.entities) {
            entities.remove(participant);
        }

        return entities.isEmpty();
    }
}
