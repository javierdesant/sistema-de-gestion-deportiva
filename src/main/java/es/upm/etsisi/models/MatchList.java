package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.util.Iterator;

public class MatchList extends List<Match> {

    public MatchList() {
        super();
    }

    public MatchList(MatchList matchList) {
        super(matchList);
    }

    @Override
    public Error add(Match match) {
        Error error;

        if (this.canAdd(match)) {
            error = super.add(match);
            assert error == null;
        } else {
            if (this.contains(match)) {
                error = Error.DUPLICATE_MATCH_ERROR;
            } else {
                error = Error.PLAYER_ALREADY_IN_MATCH_ERROR;
            }
        }

        return error;
    }

    private boolean canAdd(Match match) {
        boolean hasDuplicate = false;

        Iterator<Participant> iterator = match.getParticipants().iterator();
        while (iterator.hasNext() && !hasDuplicate) {
            hasDuplicate = this.contains(iterator.next());
        }

        return !hasDuplicate;
    }

    public void remove(String name) {
        for (Match match : this.getElements()) {
            for (Participant participant : match.getParticipants()) {
                if (participant.getName().equals(name))
                    this.remove(match);
            }
        }
    }

    public boolean contains(Participant participant) {
        if (participant == null) {
            return false;
        }
        return this.contains(participant.getName());
    }
}
