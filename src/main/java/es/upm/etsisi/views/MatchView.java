package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.game.Match;

import java.util.Iterator;

public class MatchView implements ModelView {
    private final Match match;

    public MatchView(Match match) {
        this.match = match;
    }

    @Override
    public void display() {
        Iterator<Entity> iterator = this.match.getEntities().iterator();

        iterator.next().show();
        do {
            System.out.println("vs");
            iterator.next().show();
        } while (iterator.hasNext());
    }
}
