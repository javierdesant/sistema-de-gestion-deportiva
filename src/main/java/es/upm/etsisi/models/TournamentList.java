package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.util.Iterator;
import java.util.LinkedList;

public class TournamentList extends List<Tournament> {
    public TournamentList() {
        super();
    }

    public TournamentList(LinkedList<Tournament> list) {
        this();
        for (Tournament tournament : list) {
            this.add(tournament);
        }
    }

    @Override
    public ErrorType add(Tournament tournament) {
        ErrorType error = super.add(tournament);

        if (error == ErrorType.DUPLICATE_ELEMENT_ERROR) {
            return ErrorType.DUPLICATE_TOURNAMENT_ERROR;
        }
        return error;
    }

    public Tournament find(String name) {
        Tournament res = null;

        Iterator<Tournament> iterator = this.iterator();
        while (iterator.hasNext() && res == null) {
            Tournament next = iterator.next();
            if (name.equals(next.getName())) {
                res = next;
            }
        }

        return res;
    }

    public boolean isFree(Participant participant) {
        boolean isPlaying = false;

        Iterator<Tournament> iterator = this.iterator();
        while (iterator.hasNext() && !isPlaying) {
            Tournament next = iterator.next();
            isPlaying = next.contains(participant) && next.isActive();
        }

        return !isPlaying;
    }
}
