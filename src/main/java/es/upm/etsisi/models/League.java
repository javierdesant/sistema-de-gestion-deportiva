package es.upm.etsisi.models;

public enum League {
    U10("Liga Sub-10"),
    U12("Liga Sub-12"),
    U14("Liga Sub-14"),
    U16("Liga Sub-16"),
    U18("Liga Sub-18"),
    U20("Liga Sub-20"),
    U23("Liga Sub-23"),
    ADULT("Liga de Adultos"),
    MASTER("Liga de Veteranos"),
    SENIOR("Liga Senior"),
    OVER_40("Liga para Mayores de 40"),
    OVER_50("Liga para Mayores de 50"),
    OVER_60("Liga para Mayores de 60");

    private final String title;

    League(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
