import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PlayerList {
    private final LinkedList<Player> players;

    PlayerList(){
        this.players = new LinkedList<>();
    }

    public void add(Player newPlayer){
        if (this.players.contains(newPlayer)) {
            throw new Error("Player already exists");
        } else {
            this.players.add(newPlayer);
        }
    }

    public void remove(Player player){
        this.players.remove(player);
    }
   
    public void show(){
        System.out.println("-----LISTA DE JUGADORES-----");
        Iterator<Player> iterator = players.listIterator();
        while (iterator.hasNext()) {
            iterator.next().showPlayer();
        }
        System.out.println("----------------------------");
    }
    
    private Player getPlayer(String playerName){
        Iterator<Player> iterator = players.listIterator();
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
        System.out.println("La puntuación de " + playerName + " ahora es " + score);
    }

    public void rank() {
        ArrayList<Player> playerList = new ArrayList<>(players);
        for (int i=0; i < playerList.size()-1; i++) {
            for (int j=0; j < playerList.size()-i-1; j++) {
                if (playerList.get(j).getScore() < playerList.get(j+1).getScore()) {
                    Player temp = playerList.get(j);
                    playerList.set(j, playerList.get(j+1));
                    playerList.set(j+1, temp);
                }
            }
        }
        System.out.println("----------RANKING-----------");
        Iterator<Player> iterator = playerList.iterator();
        while (iterator.hasNext()) {
            Player currentPlayer = iterator.next();
            currentPlayer.showPlayer();
            currentPlayer.showRank();
            System.out.println();
        }
        System.out.println("----------------------------");
        
    }    

}
