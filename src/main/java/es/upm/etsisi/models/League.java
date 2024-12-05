package es.upm.etsisi.models;

public enum League {
    U10("Liga Sub-10", "U10"),
    U12("Liga Sub-12", "U12"),
    U14("Liga Sub-14", "U14"),
    U16("Liga Sub-16", "U16"),
    U18("Liga Sub-18", "U18"),
    U20("Liga Sub-20", "U20"),
    U23("Liga Sub-23", "U23"),
    ADULT("Liga de Adultos", "ADULT"),
    MASTER("Liga de Veteranos", "MASTER"),
    SENIOR("Liga Senior", "SENIOR"),
    OVER_40("Liga para Mayores de 40", "40PLUS"),
    OVER_50("Liga para Mayores de 50", "50PLUS"),
    OVER_60("Liga para Mayores de 60", "60PLUS");

    private final String title;
    private final String code;

    League(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public static League getFromCode(String code) {
        for (League league : League.values()) {
            if (league.getCode().equals(code)) {
                return league;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return title;
    }

    private String getCode() {
        return code;
    }
}
