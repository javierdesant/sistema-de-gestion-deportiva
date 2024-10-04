import java.text.DecimalFormat;

public class Player {
    private final double DEF_SCORE = 0.0;

    private String name;
    private double score;

    Player(String newName) {
        this.name = newName;
        this.score = DEF_SCORE;
    }

    public String getName() {
        return this.name;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double newScore) {
        this.score = newScore;
    }

    public void show() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Jugador: " + this.name + " - Puntuación: " + df.format(this.score));
    }

    public void showPlayer() {
        System.out.println("Jugador: " + this.name);
    }

    public void showRank() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Puntuación: " + df.format(this.score));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return this.name.equals(player.getName());
    }
}
