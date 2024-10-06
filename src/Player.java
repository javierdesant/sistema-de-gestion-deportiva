import java.text.DecimalFormat;

public class Player {
    private String name;
    private double score;

    Player(String newName) {
        this.name = newName;
        this.score = 0;
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
        System.out.printf("Jugador: %s - Puntuación: %.2f\n", this.name, this.score);
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
