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
        boolean removed = this.players.remove(player);
        assert removed : "El jugador no existe";
    }

    public void show() {
        for (Player player : players) {
            player.showPlayer();
        }
    }

    public LinkedList<Player> getPlayers() {
        return this.players;
    }

    public void score(String playerName, double score) {
        assert isValidScore(score) : "La puntuación supera los límites";
        int index = this.players.indexOf(new Player(playerName));
        assert index != -1 : "El jugador no existe";
        this.players.get(index).setScore(score);
    }

    private boolean isValidScore(double score) {
        if (score < -999999.0)
            return false;
        else if (score > 999999.0)
            return false;
        else
            return true;
    }

    public boolean contains(Player player) {
        return this.players.contains(player);
    }

    public boolean isEmpty() {
        return this.players.isEmpty();
    }

    public void rank() {
        ArrayList<Player> playerList = new ArrayList<>(players);
        bubbleSort(playerList);
        for (Player player : playerList) {
            player.show();
            System.out.println();
        }
    }

    private void bubbleSort(ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size() - 1; i++) {
            for (int j = 0; j < playerList.size() - i - 1; j++) {
                if (playerList.get(j).getScore() < playerList.get(j + 1).getScore()) {
                    Player temp = playerList.get(j);
                    playerList.set(j, playerList.get(j + 1));
                    playerList.set(j + 1, temp);
                }
            }
        }
    }
}
