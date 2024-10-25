package es.upm.etsisi.models.entities;

public class Player implements Entity {  // TODO: implement
    private final String name;
    private double score;

    public Player(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Player(String name) {
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

    @Override
    public void add(Entity entity) {
    }

    @Override
    public void remove(Entity entity) {
    }

    public Entity getChild(int i) {
        return null;
    }
}