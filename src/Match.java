public class Match {
    private Player homePlayer;
    private Player visitingPlayer;

    Match(Player homePlayer, Player visitingPlayer){
        this.homePlayer = homePlayer;
        this.visitingPlayer = visitingPlayer;
    }

    public Player getHome(){
        return this.homePlayer;
    }
    public Player getVisiting(){
        return this.visitingPlayer;
    }
    public void showMatch(){
        System.out.println("Jugador local: " + homePlayer.getName()
                            + "\nJugador visitante: " + visitingPlayer.getName());
    }
}
