import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PlayerList {
    private final LinkedList<Player> players;

    PlayerList() {
        this.players = new LinkedList<>();
    }

    public void add(Player newPlayer) {
        if (!this.players.contains(newPlayer)) {
            this.players.add(newPlayer);
        } else {
            throw new Error("El jugador ya existe");
        }
    }

    public void remove(Player player) {
        if (!this.players.remove(player)) {
            throw new Error("El jugador no existe");
        }
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

    public void score(String playerName, double score) {    // TODO: implementar límite máximo, mínimo y de decimales
        int index = this.players.indexOf(new Player(playerName));

        if (index != -1) {
            this.players.get(index).setScore(score);
        } else {
            throw new Error("El jugador no existe");
        }
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
        Iterator<Player> iterator = playerList.iterator();
        while (iterator.hasNext()) {
            iterator.next().show();
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
