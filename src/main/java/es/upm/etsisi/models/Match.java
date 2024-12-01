package es.upm.etsisi.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Match {
    private final ParticipantList participants;

    // FIXME: incorrect interpretation of groups ?
    //  maybe they have to be even ?
    public Match(Collection<Participant> participants) {
        assert participants != null;
        assert participants.size() > 1;

        HashSet<Participant> participantSet = new HashSet<>(participants);
        assert participantSet.size() == participants.size();

        this.participants = new ParticipantList(participants);
    }

    public ParticipantList getParticipants() {
        return new ParticipantList(this.participants);
    }

    public boolean contains(Participant participant) {
        return this.participants.contains(participant);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Match that = (Match) object;
        return this.participants.equals(that.participants);
    }

    @Override
    public String toString() {
        Iterator<Participant> iterator = this.participants.iterator();

        StringBuilder res = new StringBuilder(iterator.next().getName());
        do {
            res.append(" vs ");
            res.append(iterator.next().getName());
        } while (iterator.hasNext());

        return res.toString();
    }
}
