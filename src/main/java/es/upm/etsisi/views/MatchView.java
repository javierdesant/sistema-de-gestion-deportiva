package es.upm.etsisi.views;

import es.upm.etsisi.models.Match;

public class MatchView extends View<Match> {

    public MatchView() {
        super();
    }

    @Override
    public void display(Match element) {
        this.writeln(element.toString());
    }
}
