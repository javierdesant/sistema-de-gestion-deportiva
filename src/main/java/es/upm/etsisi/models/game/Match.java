package es.upm.etsisi.models.game;

import es.upm.etsisi.models.player.Player;
import es.upm.etsisi.models.player.PlayerList;
import es.upm.etsisi.utils.Message;

import java.util.List;

public class Match {
    private final Player[] players;

    public Match(PlayerList playerList, Player firstPlayer, Player secondPlayer) {
        assert playerList.contains(firstPlayer) : Message.HOME_PLAYER_NOT_EXIST;
        assert playerList.contains(secondPlayer) : Message.VISITING_PLAYER_NOT_EXIST;
        assert !firstPlayer.equals(secondPlayer) : Message.SAME_PLAYER_ERROR;

        this.players = new Player[]{firstPlayer, secondPlayer};
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getPlayer(int index) {
        assert 0 <= index && index < this.players.length : Message.INVALID_INDEX;
        return this.players[index];
    }

    public void show() {    // TODO: implementar para cualquier numero de jugadores
        // TODO: implementar usando Message enum ?
        System.out.println(this.getPlayer(0).getName() + " vs " + this.getPlayer(1).getName());
    }

    public boolean contains(Player player) {
        return List.of(this.players).contains(player);
    }

    public boolean contains(String playerName) {
        return this.contains(new Player(playerName));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Match match = (Match) object;
        Player[] players = match.getPlayers();
        boolean result = false;
        for (int i = 0; i < players.length && !result; i++) {   // FIXME: using for as while
            for (Player player : players) {
                if (players[i].getName().equals(player.getName())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
