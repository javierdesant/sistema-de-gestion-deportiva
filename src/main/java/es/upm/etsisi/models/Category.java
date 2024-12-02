package es.upm.etsisi.models;

public enum Category {
    POINTS_SCORED("Puntos marcados", "POINTS"),
    MATCHES_WON("Partidos ganados", "MATCHES"),
    ASSIST_POINTS("Asistencias", "ASSISTS"),
    TOURNAMENTS_WON("Torneos ganados", "TOURNAMENTS"),
    MONEY_GENERATED_IN_THE_TOURNAMENT("Dinero generado en el torneo", "MONEY");

    private final String title;
    private final String code;

    Category(String title, String code) {
        this.title = title;
        this.code = code;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
