package es.upm.etsisi;

public class Match {
    private final Player[] players;

    public Match(PlayerList playerList, Player firstPlayer, Player secondPlayer) {
        assert playerList.contains(firstPlayer) : Message.HOME_PLAYER_NOT_EXIST;
        assert playerList.contains(secondPlayer) : Message.VISITING_PLAYER_NOT_EXIST;
        assert !firstPlayer.equals(secondPlayer);

        this.players = new Player[2];
        this.players[0] = firstPlayer;
        this.players[1] = secondPlayer;
    }

    public Player getPlayer(int index) {
        return this.players[index];
    }

    public void show() {
        System.out.println("Jugador: " + this.getPlayer(0).getName() + " vs Jugador: " + this.getPlayer(1).getName());
    }

    public boolean contains(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (player.equals(this.players[i]))
                return true;
        }
        return false;
    }

    public boolean contains(String playerName) {
        for (int i = 0; i < players.length; i++) {
            if (playerName.equals(players[i].getName()))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        return this.players[0].equals(this.players[1]);
    }
}
