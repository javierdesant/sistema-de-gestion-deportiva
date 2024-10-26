package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList {
    private final LinkedList<Match> matches;

    public MatchList() {
        this.matches = new LinkedList<>();
    }

    public boolean isEmpty() {
        return this.matches.isEmpty();
    }

    public void add(Match match) {
        assert this.isValidMatch(match) : Message.PLAYERS_MATCHED_ERROR;    // TODO: update messages

        this.matches.add(match);
    }

    public void remove(String name) {
        for (Match match : this.matches) {
            for (Entity entity : match.getEntities()) {
                if (entity.getName().equals(name))
                    this.matches.remove(match);
            }
        }
    }

    public boolean contains(String name) {
        boolean isMatched = false;

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isMatched) {
            Match match = iterator.next();
            isMatched = match.contains(name);
        }

        return isMatched;
    }

    private boolean isValidMatch(Match match) {
        boolean isInvalidMatch = this.matches.contains(match);

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isInvalidMatch) {
            Match currentMatch = iterator.next();
            for (Entity entity : currentMatch.getEntities()) {
                isInvalidMatch = currentMatch.contains(entity);
            }
        }
        return !isInvalidMatch;
    }

    public void show() {
        if (this.matches.isEmpty()) {
            Message.NO_MATCHES.writeln();
        } else {
            for (Match match : this.matches) {
                match.show();
            }
        }
    }

    public void clear() {
        this.matches.clear();
    }

    public void randomize(EntityList entityList) {
        assert !entityList.isEmpty() : Message.NO_PLAYERS;
        assert this.matches.isEmpty() : Message.NO_MATCHES;

        LinkedList<Entity> randomEntities = new LinkedList<>(entityList.getEntities());
        assert randomEntities.size() % 2 == 0 : Message.EVEN_PLAYERS_REQUIRED;

        Collections.shuffle(randomEntities);
        while (!randomEntities.isEmpty()) {
            this.matches.add(new Match(entityList, randomEntities.pop(), randomEntities.pop()));
        }
    }
}
