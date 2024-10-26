package es.upm.etsisi.models.game;

public enum Category {
    POINTS_SCORED("Points Scored"),
    MATCHES_WON("Matches Won"),
    ASSIST_POINTS("Assist Points"),
    TOURNAMENTS_WON("Tournaments Won"),
    MONEY_GENERATED_IN_THE_TOURNAMENT("Money Generated In The Tournament");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
