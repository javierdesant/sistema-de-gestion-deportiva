package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ParticipantList extends List<Participant> {
    public ParticipantList() {
        super();
    }

    public ParticipantList(ParticipantList participants) {
        super(participants);
    }

    public ParticipantList(Collection<Participant> participants) {  // FIXME
        super(participants);
    }

    @Override
    public boolean contains(Participant participant) {
        boolean found = super.contains(participant);

        if (!participant.hasChildren()) {
            Player player = (Player) participant;

            Iterator<Participant> iterator = this.iterator();
            while (iterator.hasNext() && !found) {
                found = iterator.next().contains(player);
            }
        }

        return found;
    }

    @Override
    public ErrorType add(Participant participant) {
        ErrorType error = super.add(participant);

        if (error == ErrorType.DUPLICATE_ELEMENT_ERROR) {
            if (participant.hasChildren()) {
                return ErrorType.DUPLICATE_TEAM_ERROR;
            } else {
                return ErrorType.DUPLICATE_PLAYER_ERROR;
            }
        }
        return error;
    }

    public Team getTeam(Player player) {
        Team res = null;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && res == null) {
            Participant participant = iterator.next();
            if (participant.contains(player)) {
                res = (Team) participant;
            }
        }

        return res;
    }

    @Override
    public boolean remove(Participant participant) {
        boolean removed = super.remove(participant);
        if (removed) {
            for (Player child : participant.getChildren()) {
                ErrorType error = this.add(child);
                assert error.isNull();
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

    public Participant find(Object key) {
        Participant res = null;

        Iterator<Participant> iterator = this.iterator();
        while (iterator.hasNext() && res == null) {
            Participant next = iterator.next();
            if (key.equals(next.getKey())) {
                res = next;
            } else if (next.hasChildren()) {
                res = this.findPlayerInTeam(key, (Team) next);
            }
        }

        return res;
    }

    private Player findPlayerInTeam(Object key, Team team) {
        Player res = null;

        Iterator<Player> iterator = team.getChildren().iterator();
        while (iterator.hasNext() && res == null) {
            Player next = iterator.next();
            if (key.equals(next.getKey())) {
                res = next;
            }
        }

        return res;
    }

    public ArrayList<Participant> findAll(Collection<Object> keys) {
        ArrayList<Participant> res = new ArrayList<>();

        Iterator<Object> iterator = keys.iterator();
        while (iterator.hasNext() && res != null) {
            Object currentItem = iterator.next();
            Iterator<DNI> dniIterator = ((Collection<DNI>) currentItem).iterator();
            while (dniIterator.hasNext()){
                Participant participant = this.find(dniIterator.next());
                if (participant != null) {
                    res.add(participant);
                }
            }
        }

        return res;
    }
}
