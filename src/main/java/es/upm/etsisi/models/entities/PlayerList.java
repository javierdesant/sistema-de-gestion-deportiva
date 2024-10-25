package es.upm.etsisi.models.entities;

import es.upm.etsisi.utils.Message;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerList {   // TODO: replace with general entity list
    private final LinkedList<Player> players;

    public PlayerList() {
        this.players = new LinkedList<>();
    }

    public void add(Player newPlayer) {
        assert !this.players.contains(newPlayer) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.players.add(newPlayer);
    }

    public void remove(Player player) {
        boolean removed = this.players.remove(player);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    public void show() {
        if (this.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            for (Player player : this.players) {
                player.showPlayer();
            }
        }
    }

    public LinkedList<Player> getPlayers() {
        return this.players;
    }

    public void score(String playerName, double score) {
        assert isValidScore(score) : Message.SCORE_OUT_OF_BOUNDS_ERROR;
        int index = this.players.indexOf(new Player(playerName));
        assert index != -1 : Message.PLAYER_DOES_NOT_EXIST_ERROR;
        this.players.get(index).setScore(score);
    }

    private boolean isValidScore(double score) {
        return -999999.0 < score && score < 999999.0;
    }

    public boolean contains(Player player) {
        return this.players.contains(player);
    }

    public boolean isEmpty() {
        return this.players.isEmpty();
    }

    public void rank() {
        if (this.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            ArrayList<Player> playerList = new ArrayList<>(players);
            bubbleSort(playerList);
            for (Player player : playerList) {
                player.show();
            }
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
