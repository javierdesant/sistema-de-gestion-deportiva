public class Player {
    private final double DEF_SCORE = 0.0;

    private String name;
    private double score;
    private boolean isMatched;
    
    Player(String newName){
        this.name = newName;
        this.score = DEF_SCORE;
        this.isMatched = false;
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

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Player player = (Player) object;
        return this.name.equals(player.getName());
    }

    public void showPlayer(){
        System.out.println("Jugador: " + this.name);
    }
    
    public void showRank(){
        System.out.println("Puntuación: " + this.score);
    }
    public boolean isMatched(){
        return isMatched;
    }
    public void setMatched(boolean status){
        this.isMatched = status;
    }
}
