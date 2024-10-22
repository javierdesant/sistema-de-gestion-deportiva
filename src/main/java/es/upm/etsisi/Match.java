package es.upm.etsisi;

public class Match {
    private final Player homePlayer;
    private final Player visitingPlayer;

    public Match(PlayerList playerList, Player homePlayer, Player visitingPlayer) {
        assert playerList.contains(homePlayer) : Message.HOME_PLAYER_NOT_EXIST;
        assert playerList.contains(visitingPlayer) : Message.VISITING_PLAYER_NOT_EXIST;
        assert !homePlayer.equals(visitingPlayer) : Message.SAME_PLAYER_ERROR;

        this.homePlayer = homePlayer;
        this.visitingPlayer = visitingPlayer;
    }

    public Player getHomePlayer() {
        return this.homePlayer;
    }

    public Player getVisitingPlayer() {
        return this.visitingPlayer;
    }

    public void show() {
        System.out.println("Jugador local: " + this.homePlayer.getName());
        System.out.println("Jugador visitante: " + this.visitingPlayer.getName());
    }

    public boolean contains(Player player) {
        return this.homePlayer.equals(player) || this.visitingPlayer.equals(player);
    }

    public boolean contains(String playerName) {
        return this.homePlayer.getName().equals(playerName) || this.visitingPlayer.getName().equals(playerName);
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
        Player homePlayer = match.getHomePlayer();
        Player visitingPlayer = match.getVisitingPlayer();
        return this.homePlayer.equals(homePlayer) && this.visitingPlayer.equals(visitingPlayer) ||
                this.homePlayer.equals(visitingPlayer) && this.visitingPlayer.equals(homePlayer);
    }
}
