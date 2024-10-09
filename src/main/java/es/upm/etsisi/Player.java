package es.upm.etsisi;

public class Player {
    private final String name;
    private double score;

    Player(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    Player(String name) {
        this(name, 0.0);
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
        System.out.printf("Puntuación: %.2f\n", this.score);
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
