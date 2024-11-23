package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

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
        if (removed) {
            for (Player child : participant.getChildren()) {
                Error error = this.add(child);
                assert error == null;
            }
        } else if (!participant.hasChildren()) {
            removed = this.removeFromTeam((Player) participant);
        }

        return removed;
    }

    private boolean removeFromTeam(Player player) {
        boolean removed = false;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && !removed) {
            Participant next = iterator.next();
            if (next.contains(player)) {
                removed = this.removeFromTeam(player, (Team) next);
                assert removed;
            }
        }

        return removed;
    }

    private boolean removeFromTeam(Player player, Team team) {
        if (team.size() < 2) {
            return super.remove(team);
        } else {
            return team.remove(player);
        }
    }

    public Participant find(String name) {
        Participant res = null;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && res == null) {
            Participant next = iterator.next();
            if (name.equals(next.getName())) {
                res = next;
            } else if (next.hasChildren()) {
                res = this.findPlayerInTeam(name, (Team) next);
            }
        }

        return res;
    }

    private Participant findPlayerInTeam(String name, Team team) {
        Participant res = null;

        Iterator<Player> iterator = team.getChildren().iterator();
        while (iterator.hasNext() && res == null) {
            Player next = iterator.next();
            if (name.equals(next.getName())) {
                res = next;
            }
        }

        return res;
    }

    public Team findTeam(Player player) {
        Team team = null;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && team == null) {
            Participant next = iterator.next();
            if (next.contains(player)) {
                team = (Team) next;
            }
        }

        return team;
    }
}
