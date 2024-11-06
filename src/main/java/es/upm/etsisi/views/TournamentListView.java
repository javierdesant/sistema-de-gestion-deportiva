package es.upm.etsisi.views;

import es.upm.etsisi.models.game.Tournament;
import es.upm.etsisi.models.game.TournamentList;
import es.upm.etsisi.utils.Message;

public class TournamentListView implements ModelView {
    private final TournamentList tournamentList;

    public TournamentListView(TournamentList tournamentList) {
        this.tournamentList = tournamentList;
    }

    @Override
    public void display() {
        if (this.tournamentList.isEmpty()) {
            Message.NO_MATCHES.writeln();
        } else {
            for (Tournament tournament : this.tournamentList.getElements()) {
                tournament.show();
            }
        }
    }
}
