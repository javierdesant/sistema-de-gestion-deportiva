package es.upm.etsisi.views;

import es.upm.etsisi.models.Tournament;

public class TournamentView extends View<Tournament> {

    public TournamentView() {
        super();
    }

    public void display(Tournament tournament) {
        this.writeln(tournament.getName());
        this.writeln(tournament.getStartDate() + "-" + tournament.getEndDate());
        this.writeln("Liga: " + tournament.getLeague() + " de " + tournament.getSport());

        new MatchListView().write(tournament.getMatches());
    }
}
