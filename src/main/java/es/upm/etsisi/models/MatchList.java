package es.upm.etsisi.models;

import es.upm.etsisi.exceptions.DuplicateElementException;

import java.util.Iterator;

public class MatchList extends List<Match> {

    public MatchList() {
        super();
    }

    @Override
    public void add(Match match) throws DuplicateElementException {
        Participant duplicate = null;

        Iterator<Participant> iterator = match.getParticipants().iterator();
        while (iterator.hasNext() && duplicate == null) {
            Participant next = iterator.next();
            if (this.contains(next)) {
                duplicate = next;
            }
        }

        if (duplicate == null) {
            super.add(match);
        } else {
            if (this.contains(match)) {
                throw new DuplicateElementException(match.toString());
            } else {
                throw new DuplicateElementException(duplicate.getName());
            }
        }
    }

    public void remove(String name) {
        for (Match match : this.getElements()) {
            for (Participant participant : match.getParticipants()) {
                if (participant.getName().equals(name))
                    this.remove(match);
            }
        }
    }

    public boolean contains(String name) {
        boolean found = false;

        Iterator<Match> iterator = this.getElements().iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(name);
        }

        return found;
    }

    public boolean contains(Participant participant) {
        boolean found = false;

        Iterator<Match> iterator = this.getElements().iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(participant);
        }

        return found;
    }
}
