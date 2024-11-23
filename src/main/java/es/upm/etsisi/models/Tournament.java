package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.time.LocalDate;
import java.util.*;

public class Tournament {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Sport sport;
    private final League league;
    private final Category category;
    private final MatchList matchList;

    public Tournament(
            String name,
            LocalDate startDate,
            LocalDate endDate,
            Sport sport,
            League league,
            Category category
    ) {
        assert startDate.isBefore(endDate);

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sport = sport;
        this.league = league;
        this.category = category;
        this.matchList = new MatchList();
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return this.name;
    }

    public MatchList getMatches() {
        return new MatchList(this.matchList);
    }

    public Sport getSport() {
        return this.sport;
    }

    public League getLeague() {
        return this.league;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public boolean isActive() {
        LocalDate now = LocalDate.now();

        return this.startDate.isBefore(now) && this.endDate.isAfter(now);
    }

    public boolean contains(Participant participant) {
        return this.matchList.contains(participant);
    }

    public Error matchmake(Collection<Participant> participants) {
        if (participants.size() < 2) {
            return Error.INVALID_MATCH;
        } else if (this.hasRepeatedParticipants(participants)) {
            return Error.PARTICIPANT_ALREADY_ASSIGNED_ERROR;
        } else {
            return null;
        }
    }

    private boolean hasRepeatedParticipants(Collection<Participant> participants) {
        Set<Participant> seenParticipants = new HashSet<>();
        boolean repeated = false;

        Iterator<Participant> iterator = participants.iterator();
        while (iterator.hasNext() && !repeated) {
            Participant next = iterator.next();
            repeated = !seenParticipants.add(next) || this.matchList.contains(next);
            if (next.hasChildren() && !repeated) {
                repeated = this.hasRepeatedChildren(next);
            }
        }

        return repeated;
    }

    private boolean hasRepeatedChildren(Participant participant) {
        Set<Player> seenPlayers = new HashSet<>();
        boolean repeated = false;

        Iterator<Player> iterator = participant.getChildren().iterator();
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
