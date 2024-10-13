package es.upm.etsisi;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList {
    private final LinkedList<Match> matches;

    MatchList() {
        this.matches = new LinkedList<>();
    }

    public void add(Match newMatch) {
        Player homePlayer = newMatch.getHomePlayer();
        Player visitingPlayer = newMatch.getVisitingPlayer();
        boolean isInvalidMatch = this.matches.contains(newMatch);

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isInvalidMatch) {
            Match match = iterator.next();
            isInvalidMatch = match.getHomePlayer().equals(homePlayer) ||
                    match.getHomePlayer().equals(visitingPlayer) ||
                    match.getVisitingPlayer().equals(homePlayer) ||
                    match.getVisitingPlayer().equals(visitingPlayer);
        }

        assert !isInvalidMatch : Message.PLAYERS_MATCHED_ERROR;
        this.matches.add(newMatch);
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
