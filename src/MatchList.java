import java.util.Collections;
import java.util.LinkedList;

public class MatchList{
    private final LinkedList<Match> matches;

    MatchList(){
        this.matches = new LinkedList<Match>();
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