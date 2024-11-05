package es.upm.etsisi.views;

import es.upm.etsisi.models.game.Match;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.utils.Message;

public class MatchListView implements ModelView {
    private final MatchList matchList;

    public MatchListView(MatchList matchList) {
        this.matchList = matchList;
    }

    @Override
    public void display() {
        if (this.matchList.isEmpty()) {
            Message.NO_MATCHES.writeln();
        } else {
            for (Match match : this.matchList.getElements()) {
                match.show();
            }
        }
    }
}
