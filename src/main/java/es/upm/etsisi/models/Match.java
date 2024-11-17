package es.upm.etsisi.models;

import es.upm.etsisi.utils.Message;

import java.util.*;

public class Match {
    private final ArrayList<Participant> participants;

    public Match(ParticipantList participantList, Collection<Participant> participants) {
        assert participants != null;
        assert participants.size() >= 2;                    // TODO: update messages
        assert participantList.containsAll(participants);
        for (Participant participant : participants) {
            assert Collections.frequency(participants, participant) == 1;    // FIXME: for break ?
        }                                                            // TODO: improve...

        this.participants = new ArrayList<>(participants);
    }

    public Match(ParticipantList participantList, Participant... participants) {
        this(participantList, Arrays.asList(participants));  // FIXME: maybe is null ?
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.participants);
    }

    public Participant getParticipant(int index) {
        assert 0 <= index && index < this.participants.size() : Message.INVALID_INDEX;
        return this.participants.get(index);
    }

    public boolean contains(Participant participant) {
        return this.participants.contains(participant);
    }

    public boolean contains(String entityName) {
        boolean found = false;

        Iterator<Participant> iterator = this.participants.iterator();
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
        ArrayList<Participant> participants = new ArrayList<>(match.getParticipants());

        if (this.participants.size() != participants.size()) {
            return false;
        }

        for (Participant participant : this.participants) {
            participants.remove(participant);
        }

        return participants.isEmpty();
    }
}
