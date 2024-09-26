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
    
    public void setScore(double newScore){
        this.score = newScore;
    }

    public void showPlayer(){
        System.out.println("Jugador: " + this.name + 
                           "\n Puntuación: " + this.score);
    }

}
