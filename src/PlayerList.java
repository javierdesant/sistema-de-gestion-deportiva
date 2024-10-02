import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerList {
    private final LinkedList<Player> players;

    PlayerList() {
        this.players = new LinkedList<>();
    }

    public void add(Player newPlayer) {
        assert !this.players.contains(newPlayer) : "El jugador ya existe";
        this.players.add(newPlayer);
    }

    public void remove(Player player) {
        assert this.players.remove(player) : "El jugador no existe";
    }

    public void show() {
        System.out.println("-----LISTA DE JUGADORES-----");
        for (Player player : players) {
            player.showPlayer();
        }
        System.out.println("----------------------------");
    }

    public LinkedList<Player> getPlayers() {
        return this.players;
    }

    public void score(String playerName, double score) { // TODO: implementar límite máximo, mínimo y de decimales
        int index = this.players.indexOf(new Player(playerName));
        assert index != -1 : "El jugador no existe";
        this.players.get(index).setScore(score);
    }

    public boolean contains(Player player) {
        return this.players.contains(player);
    }

    public void rank() {
        ArrayList<Player> playerList = new ArrayList<>(players);
        for (int i = 0; i < playerList.size() - 1; i++) {
            for (int j = 0; j < playerList.size() - i - 1; j++) {
                if (playerList.get(j).getScore() < playerList.get(j + 1).getScore()) {
                    Player temp = playerList.get(j);
                    playerList.set(j, playerList.get(j + 1));
                    playerList.set(j + 1, temp);
                }
            }
        }
        System.out.println("----------RANKING-----------");
        for (Player player : playerList) {
            player.show();
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
