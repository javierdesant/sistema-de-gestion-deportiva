package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.util.Iterator;

public class MatchList extends List<Match> {

    public MatchList() {
        super();
    }

    public MatchList(MatchList matchList) {
        super(matchList);
    }

    @Override
    public ErrorType add(Match match) {
        ErrorType error;

        if (this.canAdd(match)) {
            error = super.add(match);
            assert error.isNull();
        } else {
            if (this.contains(match)) {
                error = ErrorType.DUPLICATE_MATCH_ERROR;
            } else {
                error = ErrorType.PLAYER_ALREADY_IN_MATCH_ERROR;
            }
        }

        return error;
    }

    private boolean canAdd(Match match) {
        boolean hasDuplicate = false;

        Iterator<Participant> iterator = match.getParticipants().iterator();
        while (iterator.hasNext() && !hasDuplicate) {
            Participant participant = iterator.next();
            hasDuplicate = this.contains(participant);
            Iterator<Player> playerIterator = participant.getMembers().iterator();
            while (playerIterator.hasNext() && !hasDuplicate) {
                Player player = playerIterator.next();
                hasDuplicate = this.contains(player);
            }
        }

        return !hasDuplicate;
    }

    public void remove(String name) {
        for (Match match : this.getElements()) {
            for (Participant participant : match.getParticipants().getElements()) {
                if (participant.getName().equals(name))
                    this.remove(match);
            }
        }
    }

    public boolean contains(Participant participant) {
        boolean found = false;

        Iterator<Match> iterator = this.iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(participant);
        }

        return found;
    }
}
