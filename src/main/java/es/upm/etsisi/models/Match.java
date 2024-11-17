package es.upm.etsisi.models;

import java.util.*;

public class Match {
    private final HashSet<Participant> participants;

    public Match(ParticipantList participantList, Collection<Participant> participants) {
        assert participants != null;
        assert participants.size() > 1;

        if (!participantList.containsAll(participants)) {
            throw new IllegalArgumentException("El participante no existe en la lista.");
        }

        HashSet<Participant> participantSet = new HashSet<>(participants);
        if (participantSet.size() == participants.size()) {
            this.participants = participantSet;
        } else {
            throw new IllegalArgumentException("La lista de participantes contiene duplicados.");
        }
    }

    public Match(ParticipantList participantList, Participant... participants) {
        this(participantList, Arrays.asList(participants));
    }

    public HashSet<Participant> getParticipants() {
        return new HashSet<>(this.participants);
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
        return this.participants.equals(match.getParticipants());
    }

    @Override
    public String toString() {
        Iterator<Participant> iterator = this.getParticipants().iterator();

        StringBuilder res = new StringBuilder(iterator.next().getName());
        do {
            res.append(" vs ");
            res.append(iterator.next().getName());
        } while (iterator.hasNext());

        return res.toString();
    }
}
