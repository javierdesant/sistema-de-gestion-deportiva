package es.upm.etsisi.models.game;

import java.time.LocalDate;

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
            Category category,
            MatchList matchList
    ) {
        assert startDate.isBefore(endDate);

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sport = sport;
        this.league = league;
        this.category = category;
        this.matchList = matchList;
    }

    public String getName() {
        return this.name;
    }

    public MatchList getMatches() {
        return this.matchList;
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
}
