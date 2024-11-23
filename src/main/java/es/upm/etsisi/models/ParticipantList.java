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

    @Override
    public boolean remove(Participant participant) {
        boolean removed = super.remove(participant);
        Player player = (Player) participant;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && !removed) {
            Participant next = iterator.next();
            if (next.contains(player)) {
                Team team = (Team) next;
                if (team.size() < 2) {
                    removed = super.remove(team);
                } else {
                    removed = team.remove(player);
                }
                assert removed;
            }
        }

        return removed;
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
