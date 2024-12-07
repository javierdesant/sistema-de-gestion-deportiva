package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

import java.time.LocalDate;
import java.util.*;

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
        return this.timeFrame.getStartDate();
    }

    public LocalDate getEndDate() {
        return this.timeFrame.getEndDate();
    }

    public boolean isActive() {
        return this.timeFrame.includesNow();
    }

    public boolean contains(Participant participant) {
        return this.matchList.contains(participant);
    }

    public ErrorType enroll(Participant participant) {
        return this.participantList.add(participant);
    }

    public ErrorType remove(Participant participant) {
        if (!this.participantList.contains(participant)) {
            return ErrorType.PARTICIPANT_NOT_FOUND;
        }

        for (Match match : this.matchList.getElements()) {
            if (match.contains(participant)) {
                this.matchList.remove(match);
            }
        }
        boolean removed = this.participantList.remove(participant);
        assert removed;
        return ErrorType.NULL;
    }

    public ErrorType matchmake(Collection<Participant> participants) {
        ErrorType error;

        if (participants.size() < 2) {
            error = ErrorType.INVALID_MATCH;
        } else if (!this.participantList.containsAll(participants)) {
            error = ErrorType.PARTICIPANT_NOT_ENROLLED;
        } else if (this.hasRepeatedParticipants(participants)) {
            error = ErrorType.PARTICIPANT_ALREADY_ASSIGNED_ERROR;
        } else {
            error = this.matchList.add(new Match(participants));
            assert error.isNull();
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

        Iterator<Player> iterator = team.getMembers().iterator();
        while (iterator.hasNext() && !repeated) {
            Player next = iterator.next();
            repeated = !seenPlayers.add(next) || this.matchList.contains(next);
        }

        return repeated;
    }

    public ErrorType randomMatchmake(int groupSize) {
        ErrorType error;
        LinkedList<Participant> participants = new LinkedList<>(this.participantList.getElements());

        if (participants.size() < 2 || groupSize < 2 || Integer.bitCount(groupSize) != 1 || groupSize > participants.size()) {
            return ErrorType.INVALID_MATCH;
        }
        this.matchList.clear();
        Collections.shuffle(participants);
        for (int i = participants.size() - 1; i >= groupSize - 1; i -= groupSize) {
            LinkedList<Participant> group = new LinkedList<>();
            for (int j = 0; j < groupSize; j++) {
                group.add(participants.remove(i - j));
            }
            error = this.matchmake(group);
            assert error.isNull();
        }

        return ErrorType.NULL;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Tournament that = (Tournament) object;
        return this.getName().equals(that.getName());
    }
}
