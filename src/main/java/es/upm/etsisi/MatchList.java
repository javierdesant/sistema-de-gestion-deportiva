package es.upm.etsisi;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList {
    private final LinkedList<Match> matches;

    MatchList() {
        this.matches = new LinkedList<>();
    }

    public boolean isEmpty() {
        return this.matches.isEmpty();
    }

    public void add(Match newMatch) {
        assert this.isValidMatch(newMatch) : Message.PLAYERS_MATCHED_ERROR;

        this.matches.add(newMatch);
    }

    public void remove(String playerName) {
        for (Match match : this.matches) {
            if (match.getHomePlayer().getName().equals(playerName)
                    || match.getVisitingPlayer().getName().equals(playerName)) {
                this.matches.remove(match);
            }
        }
    }

    public boolean isMatched(String playerName) {
        boolean isMatched = false;
        for (Match match : this.matches) {  // TODO: convertir en un while
            if (match.getHomePlayer().getName().equals(playerName)
                    || match.getVisitingPlayer().getName().equals(playerName)) {
                isMatched = true;
            }
        }
        return isMatched;
    }

    private boolean isValidMatch(Match match) {
        Player homePlayer = match.getHomePlayer();
        Player visitingPlayer = match.getVisitingPlayer();
        boolean isInvalidMatch = this.matches.contains(match);

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isInvalidMatch) {
            Match currentMatch = iterator.next();
            isInvalidMatch = currentMatch.getHomePlayer().equals(homePlayer) ||
                    currentMatch.getHomePlayer().equals(visitingPlayer) ||
                    currentMatch.getVisitingPlayer().equals(homePlayer) ||
                    currentMatch.getVisitingPlayer().equals(visitingPlayer);
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

    public void randomize(PlayerList playerList) {
        assert !playerList.isEmpty() : Message.NO_PLAYERS;
        assert this.matches.isEmpty() : Message.NO_MATCHES;

        LinkedList<Player> randomPlayers = new LinkedList<>(playerList.getPlayers());
        assert randomPlayers.size() % 2 == 0 : Message.EVEN_PLAYERS_REQUIRED;

        Collections.shuffle(randomPlayers);
        while (!randomPlayers.isEmpty()) {
            this.matches.add(new Match(playerList, randomPlayers.pop(), randomPlayers.pop()));
        }
    }
}
