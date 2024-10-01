import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList {
    private final LinkedList<Match> matches;

    MatchList() {
        this.matches = new LinkedList<Match>();
    }

    public void add(PlayerList playerList, Match newMatch) {
        Player homePlayer = newMatch.getHomePlayer();
        Player visitingPlayer = newMatch.getVisitingPlayer();

        boolean isValidMatch = !this.matches.contains(newMatch);

        Iterator<Match> iterator = this.matches.iterator();

        while (isValidMatch && iterator.hasNext()) {
            Match match = iterator.next();
            isValidMatch = !match.getHomePlayer().equals(homePlayer) &&
                    !match.getHomePlayer().equals(visitingPlayer) &&
                    !match.getVisitingPlayer().equals(homePlayer) &&
                    !match.getVisitingPlayer().equals(visitingPlayer);
        }

        if (!isValidMatch) {
            throw new Error("Uno de los jugadores ya está emparejado en otro partido");
        } else {
            this.matches.add(newMatch);
        }
    }

    public void show() {
        System.out.println("------EMPAREJAMIENTOS-------");

        if (this.matches.isEmpty()) {
            System.out.println("No hay emparejamientos");
        } else {
            for (Match match : this.matches) {
                match.show();
            }
        }

        System.out.println("----------------------------");
    }

    public void clear() {
        this.matches.clear();
    }

    public void randomize(PlayerList playerList) {  // TODO: contemplar posibles emparejamientos previos
        LinkedList<Player> randomPlayers = new LinkedList<>(playerList.getPlayers());

        if (randomPlayers.size() % 2 == 0) {
            Collections.shuffle(randomPlayers);
            while (!randomPlayers.isEmpty()) {
                this.matches.add(new Match(playerList, randomPlayers.pop(), randomPlayers.pop()));
            }
            System.out.println("Emparejamiento aleatorio realizado con éxito.");
        } else {
            throw new Error("El número de jugadores debe ser par");
        }
    }
}