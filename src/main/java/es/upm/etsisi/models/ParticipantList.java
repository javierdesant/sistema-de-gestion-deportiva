package es.upm.etsisi.models;

import java.util.Collection;
import java.util.Iterator;

public class ParticipantList extends List<Participant> {
    public ParticipantList() {
        super();
    }

    public ParticipantList(ParticipantList participants) {
        super(participants);
    }

    public ParticipantList(Collection<Participant> participants) {
        super(participants);
    }

    @Override
    public boolean contains(Participant participant) {
        boolean found = super.contains(participant);
        Player player = (Player) participant;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(player);
        }

        return found;
    }

    public Participant find(String name) {
        Participant res = null;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && res == null) {
            Participant participant = iterator.next();
            if (participant.getName().equals(name)) {
                res = participant;
            }
        }

        return res;
    }
}
