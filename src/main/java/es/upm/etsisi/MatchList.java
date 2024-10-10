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
        assert !isValidMatch(newMatch) : "Los jugadores deben estar sin emparejar";
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
        for (Match match : this.matches) {
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
        boolean isValid = this.matches.contains(match);
        if (!isValid) {
            Iterator<Match> iterator = this.matches.iterator();
            while (iterator.hasNext() && !isValid) {
                Match currentMatch = iterator.next();
                isValid = currentMatch.getHomePlayer().equals(homePlayer) ||
                        currentMatch.getHomePlayer().equals(visitingPlayer) ||
                        currentMatch.getVisitingPlayer().equals(homePlayer) ||
                        currentMatch.getVisitingPlayer().equals(visitingPlayer);
            }
        }
        return isValid;
    }

    public void show() {
        if (this.matches.isEmpty()) {
            System.out.println("No hay emparejamientos");
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
        assert !playerList.isEmpty() : "No hay jugadores";
        assert this.matches.isEmpty() : "Los emparejamientos deben estar vacíos";

        LinkedList<Player> randomPlayers = new LinkedList<>(playerList.getPlayers());
        assert randomPlayers.size() % 2 == 0 : "El número de jugadores debe ser par";

        Collections.shuffle(randomPlayers);
        while (!randomPlayers.isEmpty()) {
            this.matches.add(new Match(playerList, randomPlayers.pop(), randomPlayers.pop()));
        }
        System.out.println("Emparejamiento aleatorio realizado con éxito.");
    }
}
