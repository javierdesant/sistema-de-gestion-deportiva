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

        assert !this.matches.contains(newMatch) : "El emparejamiento ya existe";

        Iterator<Match> iterator = this.matches.iterator();

        while (iterator.hasNext()) { // TODO: usar .stream para simplificar la lógica
            Match match = iterator.next();
            assert !match.getHomePlayer().equals(homePlayer) &&
                    !match.getHomePlayer().equals(visitingPlayer) &&
                    !match.getVisitingPlayer().equals(homePlayer) &&
                    !match.getVisitingPlayer().equals(visitingPlayer) : "Uno de los jugadores ya está emparejado";
        }
        this.matches.add(newMatch);
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

    public void randomize(PlayerList playerList) { // TODO: contemplar posibles emparejamientos previos
        LinkedList<Player> randomPlayers = new LinkedList<>(playerList.getPlayers());
        assert randomPlayers.size() % 2 == 0 : "El número de jugadores debe ser par";

        Collections.shuffle(randomPlayers);
        while (!randomPlayers.isEmpty()) {
            this.matches.add(new Match(playerList, randomPlayers.pop(), randomPlayers.pop()));
        }
        System.out.println("Emparejamiento aleatorio realizado con éxito.");
    }
}