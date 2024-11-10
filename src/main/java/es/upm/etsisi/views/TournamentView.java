package es.upm.etsisi.views;

import es.upm.etsisi.models.game.Match;
import es.upm.etsisi.models.game.Tournament;
import es.upm.etsisi.utils.Message;

public class TournamentView implements ModelView {
    private final Tournament tournament;

    public TournamentView(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public void display() {
        Message.FOOTER.writeln();

        System.out.println(this.tournament.getName());
        System.out.println(this.tournament.getStartDate() + " - " + this.tournament.getEndDate());
        System.out.println(this.tournament.getSport());
        System.out.println(this.tournament.getLeague());

        for (Match match : this.tournament.getMatches().getElements()) {
            match.show();
        }

        Message.FOOTER.writeln();
    }
}
