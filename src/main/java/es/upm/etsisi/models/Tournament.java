package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Tournament {
    private final TournamentInfo info;
    private final TimeFrame timeFrame;
    private final ParticipantList participantList;
    private final MatchList matchList;

    public Tournament(TournamentInfo info, TimeFrame timeFrame) {
        this.info = info;
        this.timeFrame = timeFrame;
        this.participantList = new ParticipantList();
        this.matchList = new MatchList();
    }

    public Category getCategory() {
        return this.info.category();
    }

    public String getName() {
        return this.info.name();
    }

    public MatchList getMatches() {
        return new MatchList(this.matchList);
    }

    public Sport getSport() {
        return this.info.sport();
    }

    public League getLeague() {
        return this.info.league();
    }

    public LocalDate getStartDate() {
        return this.timeFrame.startDate();
    }

    public LocalDate getEndDate() {
        return this.timeFrame.endDate();
    }

    public boolean isActive() {
        return this.timeFrame.includesNow();
    }

    public boolean contains(Participant participant) {
        return this.matchList.contains(participant);
    }

    public Error matchmake(Collection<Participant> participants) {
        Error error;

        if (participants.size() < 2) {
            error = Error.INVALID_MATCH;
        } else if (!this.participantList.containsAll(participants)) {
            error = Error.PARTICIPANT_NOT_ENROLLED;
        } else if (this.hasRepeatedParticipants(participants)) {
            error = Error.PARTICIPANT_ALREADY_ASSIGNED_ERROR;
        } else {
            error = this.matchList.add(new Match(participants));
            assert error == null;
        }

        return error;
    }

    private boolean hasRepeatedParticipants(Collection<Participant> participants) {
        Set<Participant> seenParticipants = new HashSet<>();
        boolean repeated = false;

        Iterator<Participant> iterator = participants.iterator();
        while (iterator.hasNext() && !repeated) {
            Participant next = iterator.next();
            repeated = !seenParticipants.add(next) || this.matchList.contains(next);
            if (next.hasChildren() && !repeated) {
                repeated = this.hasRepeatedChildren((Team) next);
            }
        }

        return repeated;
    }

    private boolean hasRepeatedChildren(Team team) {
        Set<Player> seenPlayers = new HashSet<>();
        boolean repeated = false;

        Iterator<Player> iterator = team.getChildren().iterator();
        while (iterator.hasNext() && !repeated) {
            Player next = iterator.next();
            repeated = !seenPlayers.add(next) || this.matchList.contains(next);
        }

        return repeated;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Tournament tournament = (Tournament) object;
        return this.getName().equals(tournament.getName());
    }
}
