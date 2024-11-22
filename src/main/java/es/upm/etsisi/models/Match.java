package es.upm.etsisi.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Match {
    private final HashSet<Participant> participants;

    public Match(Collection<Participant> participants) {
        assert participants != null;
        assert participants.size() > 1;

        HashSet<Participant> participantSet = new HashSet<>(participants);
        assert participantSet.size() == participants.size();

        this.participants = participantSet;
    }

    public Match(Participant... participants) {
        this(Arrays.asList(participants));
    }

    public HashSet<Participant> getParticipants() {
        return new HashSet<>(this.participants);
    }

    public boolean contains(Participant participant) {
        return this.participants.contains(participant);
    }

    public boolean contains(String name) {
        boolean found = false;

        Iterator<Participant> iterator = this.participants.iterator();
        while (iterator.hasNext() && !found) {
            found = name.equals(iterator.next().getName());
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
