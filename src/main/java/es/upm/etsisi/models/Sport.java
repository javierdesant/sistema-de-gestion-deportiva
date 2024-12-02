package es.upm.etsisi.models;

public enum Sport {
    SOCCER("Futbol", "FBL"),
    BASKETBALL("Baloncesto", "BSK"),
    TENNIS("Tenis", "TEN"),
    SWIMMING("Natacion", "SWM"),
    BOXING("Boxeo", "BOX"),
    ATHLETICS("Atletismo", "ATH"),
    GOLF("Golf", "GLF"),
    CRICKET("Criquet", "CKT"),
    BASEBALL("Beisbol", "BSB"),
    RUGBY("Rugby", "RUG"),
    VOLLEYBALL("Voleibol", "VOL"),
    HANDBALL("Balonmano", "HBL"),
    CYCLING("Ciclismo", "CYC"),
    HOCKEY("Hockey", "HOC"),
    SKIING("Esqui", "SKI"),
    SNOWBOARDING("Snowboard", "SBD"),
    ICE_HOCKEY("Hockey sobre hielo", "IHO"),
    SURFING("Surf", "SRF"),
    BADMINTON("Badminton", "BDM"),
    TABLE_TENNIS("Tenis de mesa", "TTE"),
    WRESTLING("Lucha", "WRS"),
    JUDO("Judo", "JUD"),
    KARATE("Karate", "KTE"),
    TAEKWONDO("Taekwondo", "TKW"),
    FENCING("Esgrima", "FEN"),
    ARCHERY("Tiro con arco", "ARC"),
    SHOOTING("Tiro", "SHO"),
    EQUESTRIAN("Equitacion", "EQU"),
    TRIATHLON("Triatlon", "TRI"),
    WEIGHTLIFTING("Halterofilia", "WLF"),
    SAILING("Vela", "SAL"),
    ROWING("Remo", "ROW"),
    CANOEING("Piragüismo", "CAS"),
    SKATEBOARDING("Monopatín", "SKS"),
    CLIMBING("Escalada", "CLB"),
    GYMNASTICS("Gimnasia", "GYM"),
    MARTIAL_ARTS("Artes marciales", "MAR"),
    POLO("Polo", "POL"),
    MOTORSPORTS("Deportes de motor", "MCS"),
    ESPORTS("Esports", "ESP");

    private final String name;
    private final String code;

    Sport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    private String getCode() {
        return code;
    }

    public static Sport getFromCode(String code) {
        for (Sport sport : Sport.values()) {
            if (sport.getCode().equals(code)) {
                return sport;
            }
        }
        return null;
    }
}
