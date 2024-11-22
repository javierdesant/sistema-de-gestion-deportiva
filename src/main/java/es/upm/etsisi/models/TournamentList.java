package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.util.Iterator;

public class TournamentList extends List<Tournament> {
    public TournamentList() {
        super();
    }

    @Override
    public Error add(Tournament tournament) {
        Error error = super.add(tournament);

        if (error == Error.DUPLICATE_ELEMENT_ERROR) {
            return Error.DUPLICATE_TOURNAMENT_ERROR;
        }
        return error;
    }

    public Tournament find(String name) {
        Tournament res = null;

        Iterator<Tournament> iterator = this.getElements().iterator();
        while (iterator.hasNext() && res == null) {
            Tournament next = iterator.next();
            if (name.equals(next.getName())) {
                res = next;
            }
        }

        return res;
    }

    public boolean isPlaying(Participant participant) {
        boolean res = false;

        Iterator<Tournament> iterator = this.iterator();
        while (iterator.hasNext() && !res) {
            Tournament next = iterator.next();
            res = next.contains(participant) && next.isActive();
        }

        return res;
    }
}
