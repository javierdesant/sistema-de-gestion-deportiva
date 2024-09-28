import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList{
    private final LinkedList<Match> matches;

    MatchList(){
        this.matches = new LinkedList<Match>();
    }

    public void add(Match newMatch){
        this.matches.add(newMatch);
    }

    public void show(){
        System.out.println("------EMPAREJAMIENTOS-------");
        if (this.matches.isEmpty()) {
            System.out.println("No hay emparejamientos");
        } else {
            Iterator<Match> iterator = this.matches.descendingIterator();
            while (iterator.hasNext()) {
                iterator.next().showMatch();
                System.out.println();
            }
        }
        System.out.println("----------------------------");
    }
    
    public void clear(){
        matches.clear();
    }
    
    public void randomize(PlayerList playerList){
        LinkedList<Player> players = playerList.getPlayers();
        if (players.size() % 2 == 0) {
            LinkedList<Player> randomPlayers = new LinkedList<>(players);
            Collections.shuffle(randomPlayers);
            while (!randomPlayers.isEmpty()) {
                this.matches.add(new Match(randomPlayers.pop(), randomPlayers.pop()));
            }
        } else {
            throw new Error("Players amount must be even");
        }
    }
}