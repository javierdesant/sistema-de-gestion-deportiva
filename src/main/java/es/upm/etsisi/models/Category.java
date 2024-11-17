package es.upm.etsisi.models;

public enum Category {
    POINTS_SCORED("Puntos marcados"),
    MATCHES_WON("Partidos ganados"),
    ASSIST_POINTS("Asistencias"),
    TOURNAMENTS_WON("Torneos ganados"),
    MONEY_GENERATED_IN_THE_TOURNAMENT("Dinero generado en el torneo");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
