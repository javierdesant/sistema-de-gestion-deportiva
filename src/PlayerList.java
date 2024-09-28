import java.util.Iterator;
import java.util.LinkedList;

public class PlayerList {
    private final LinkedList<Player> players;

    PlayerList(){
        this.players = new LinkedList<>();
    }

    

    // TODO: estoy trabjando en esto
    public void add(Player newPlayer){
        if (this.players.contains(newPlayer)) {
            throw new Error("Player already exists");
        } else {
            this.players.add(newPlayer);
        }
    }
    
    public void create(String playerName){
        Player newPlayer = new Player(playerName);
        Iterator<Player> iterator = players.iterator();
        boolean exists = false;
        while (iterator.hasNext() && !exists) {
            exists = iterator.next().getName().equals(playerName);
        }
        if (exists) {
            throw new Error("Player already exists");
        } else {
            this.players.add(newPlayer);
        }
    }
    // TODO: estoy trabjando en esto



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
