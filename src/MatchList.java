import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MatchList{
    private final LinkedList<Match> matches;

    MatchList(){
        this.matches = new LinkedList<Match>();
    }
    private void addMatch(Match newMatch){
        this.matches.add(newMatch);
    }
    public void matchmake(PlayerList playerList, String home, String visiting){
        try{
            Player homePlayer = playerList.getPlayers().get(playerList.getPlayers().indexOf(new Player(home)));
            Player visitingPlayer = playerList.getPlayers().get(playerList.getPlayers().indexOf(new Player(visiting)));
            if(!homePlayer.isMatched() && !visitingPlayer.isMatched()){
                Match newMatch = new Match(homePlayer, visitingPlayer);
                addMatch(newMatch);
                System.out.println(home + " y " + visiting + " han sido emparejados correctamente");
            } else throw new Error("Los jugadores deben de estar sin emparejar");
        }catch (IndexOutOfBoundsException e){
            throw new Error("Introduzca jugadores existentes");
        }
    }

    public void show(){
        System.out.println("------EMPAREJAMIENTOS-------\n");

        if (this.matches.isEmpty()) {
            System.out.println("No hay emparejamientos\n");
            
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
        System.out.println("matches have been cleared successfully");
    }
    
    public void randomize(PlayerList playerList){
        LinkedList<Player> players = playerList.getPlayers();
        if (players.size() % 2 == 0) {
            LinkedList<Player> randomPlayers = new LinkedList<>(players);
            Collections.shuffle(randomPlayers);
            while (!randomPlayers.isEmpty()) {
                this.matches.add(new Match(randomPlayers.pop(), randomPlayers.pop()));
            }
            System.out.println("matchmakes have been successfully created at random");
        } else {
            throw new Error("Players amount must be even");
        }
    }
}