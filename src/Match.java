public class Match {
    private Player homePlayer;
    private Player visitingPlayer;

    Match(PlayerList playerList, Player homePlayer, Player visitingPlayer) {
        if (!playerList.contains(homePlayer) && !playerList.contains(visitingPlayer)) {
            throw new Error("Los jugadores no existen");
        } else if (!playerList.contains(homePlayer)) {
            throw new Error("El jugador local no existe");
        } else if (!playerList.contains(visitingPlayer)) {
            throw new Error("El jugador visitante no existe");
        } else if (homePlayer.equals(visitingPlayer)) {
            throw new Error("Los jugadores no pueden ser el mismo");
        } else {
            this.homePlayer = homePlayer;
            this.visitingPlayer = visitingPlayer;
        }
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
