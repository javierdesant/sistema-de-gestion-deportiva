public class Player {
    private final double DEF_SCORE = 0.0;

    private String name;
    private double score;
    
    Player(String newName){
        this.name = newName;
        this.score = DEF_SCORE;
    }

    public String getName(){
        return this.name;
    }

    public double getScore(){
        return this.score;
    }
    
    public void setScore(double newScore){
        this.score = newScore;
    }

    public boolean equals(Player player){
        return this.name.equals(player.getName());
    }

    public void showPlayer(){
        System.out.println("Jugador: " + this.name);
    }
    
    public void showRank(){
        System.out.println("Puntuación: " + this.score);
    }
}
