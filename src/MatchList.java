import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList{
    private final LinkedList<Match> matches;

    MatchList(){
        this.matches = new LinkedList<Match>();
    }

    public void show_matchmake(){
        System.out.println("------EMPAREJAMIENTOS-------\n");
        Iterator<Match> iterator = matches.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next().showMatch();
            System.out.println();
        }
        System.out.println("----------------------------\n");
    }
    public void clear_matchmake(){
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