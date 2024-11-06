package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.game.Match;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class MatchView implements ModelView {
    private final Match match;

    public MatchView(Match match) {
        this.match = match;
    }

    @Override
    public void display() {
        Iterator<Entity> iterator = this.match.getEntities().iterator();

        Message.LIGHT_FOOTER.writeln();
        iterator.next().show();
        do {
            System.out.println("vs");
            iterator.next().show();
        } while (iterator.hasNext());
        Message.LIGHT_FOOTER.writeln();
    }
}
