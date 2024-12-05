package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Match;
import es.upm.etsisi.utils.Console;

public class MatchListView extends ListView<Match> {
    private final MatchView MatchView;
    private Console console;

    public MatchListView() {
        super();
        MatchView = new MatchView();
    }

    @Override
    public void write(List<Match> list) {
        console.writeln("     PARTIDOS     ");
        console.writeln("------------------");
        this.writeList(list);
    }

    @Override
    protected void write(Match match) {
        MatchView.write(match);
    }
}
