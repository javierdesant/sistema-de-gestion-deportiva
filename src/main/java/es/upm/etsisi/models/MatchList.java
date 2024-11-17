package es.upm.etsisi.models;

import java.util.Iterator;

public class MatchList extends List<Match> {

    public MatchList() {
        super();
    }

    @Override
    public void add(Match match) {
        assert this.isValidMatch(match);    // TODO: use exception

        this.addElement(match);
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

    private boolean isValidMatch(Match match) {
        boolean isValid = !this.contains(match);

        Iterator<Participant> iterator = match.getParticipants().iterator();
        while (iterator.hasNext() && isValid) {
            isValid = !this.contains(iterator.next());
        }

        return isValid;
    }
}
