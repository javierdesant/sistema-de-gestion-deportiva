import java.util.Iterator;
import java.util.LinkedList;

public class PlayerList {
    private final LinkedList<Player> players;

    PlayerList(){
        this.players = new LinkedList<>();
    }
    
    public void create(String playerName){
        this.players.add(new Player(playerName));
    }

    public void remove(Player player){
        this.players.remove(player);
    }
   
    public void show(){
        System.out.println("-----LISTA DE JUGADORES-----");
        Iterator<Player> iterator = players.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next().showPlayer();
        }
        System.out.println("----------------------------\n");
    }
    
    private Player getPlayer(String playerName){
        Iterator<Player> iterator = players.descendingIterator();
        Player res = null;
        while(iterator.hasNext() && res == null){
            Player currentPlayer = iterator.next();
            if(currentPlayer.getName().equals(playerName))
                res = currentPlayer;
        }   
        return res;
    }

    public LinkedList<Player> getPlayers(){
        return this.players;
    }

    public void score(String playerName, double score){
        this.getPlayer(playerName).setScore(score);
    }

    //TODO Hacer que muestre nombre y puntuación de jugadores ordenado descendentemente (supongo que por puntuación)
    public void rank(){

    }
    

}
