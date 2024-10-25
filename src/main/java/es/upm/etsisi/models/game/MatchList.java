package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.PlayerList;
import es.upm.etsisi.utils.Message;

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
        assert this.isValidMatch(match) : Message.PLAYERS_MATCHED_ERROR;

        this.matches.add(match);
    }

    public void remove(String playerName) {
        for (Match match : this.matches) {
            for (Player player : match.getPlayers()) {
                if (player.getName().equals(playerName))
                    this.matches.remove(match);
            }
        }
    }

    public boolean contains(String playerName) {
        boolean isMatched = false;

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isMatched) {
            Match match = iterator.next();
            isMatched = match.contains(playerName);
        }

        return isMatched;
    }

    private boolean isValidMatch(Match match) {
        boolean isInvalidMatch = this.matches.contains(match);

        Iterator<Match> iterator = this.matches.iterator();
        while (iterator.hasNext() && !isInvalidMatch) {
            Match currentMatch = iterator.next();
            Player[] currentPlayers = currentMatch.getPlayers();
            for (Player player : currentPlayers) {
                isInvalidMatch = currentMatch.contains(player);
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
