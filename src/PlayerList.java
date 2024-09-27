import java.util.Iterator;
import java.util.LinkedList;

public class PlayerList {
    private LinkedList<Player> players;

    PlayerList(){
        this.players = new LinkedList<Player>();
    }
    
    public void create(String playerName){
        this.players.add(new Player(playerName));
    }

    public void remove(Player player){
        this.players.remove(player);
    }
   
    public void show(){
        Iterator<Player> iterator = players.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next().showPlayer();
        }
    }
    
    private Player getPlayer(String playerName){
        Iterator<Player> iterator = players.descendingIterator();
        Player res = null;
        while(iterator.hasNext() && res == null){
            Player currentPlayer = iterator.next();
            if(currentPlayer.getName() == playerName)
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
