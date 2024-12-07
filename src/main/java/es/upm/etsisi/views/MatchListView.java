package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Match;

public class MatchListView extends ListView<Match> {
    public MatchListView() {
        super();
    }

    @Override
    public void write(List<Match> list) {
        this.writeln("---- PARTIDOS ----");
        this.writeList(list);
        this.writeln("------------------");
    }

    @Override
    public void display(Match match) {
        new MatchView().display(match);
    }
}
