package es.upm.etsisi.models.tournament;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.game.Sport;

import java.util.Date;

public class Tournament {
    private final String name;
    private final Date startDate;
    private final Date endDate;
    private final Sport sport;
    // private final League league;   // TODO
    private final Category category;
    private final MatchList matchList;

    public Tournament(String name, Date startDate, Date endDate, Sport sport, Category category, MatchList matchList) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sport = sport;
        this.category = category;
        this.matchList = matchList;
    }
}
