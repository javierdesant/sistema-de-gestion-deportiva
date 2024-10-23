package es.upm.etsisi;

public class Match {
    private final Player[] players;

    public Match(PlayerList playerList, Player fP, Player sP) {
        assert playerList.contains(fP) : Message.HOME_PLAYER_NOT_EXIST;
        assert playerList.contains(sP) : Message.VISITING_PLAYER_NOT_EXIST;
        assert !fP.equals(sP);

        this.players = new Player[] { fP, sP };
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getPlayer(int index) {
        assert index >= 0 && index < this.players.length : "Índice de jugador no válido";
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
        Match match = (Match) object;
        Player[] players = new Player[this.players.length];
        boolean result = false;
        players = match.getPlayers();
        for (int i = 0; i < players.length && !result; i++) {
            for (int j = 0; j < players.length; j++) {
                if (players[i].getName().equals(players[j].getName())) {
                    result = true;
                }
            }
        }
        return result;
    }
}
