package es.upm.etsisi.models.game;

public enum Categories {
    POINTS_SCORED("Points Scored"),
    MATCHES_WON("Matches Won"),
    ASSIST_POINTS("Assist Points"),
    TOURNAMENTS_WON("Tournaments Won"),
    MONEY_GENERATED_IN_THE_TOURNAMENT("Money Generated In The Tournament"),;

    private final String title;

    Categories(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
