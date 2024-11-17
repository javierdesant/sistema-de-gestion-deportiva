package es.upm.etsisi.models;

public enum Sport {
    SOCCER("Futbol"),
    BASKETBALL("Baloncesto"),
    TENNIS("Tenis"),
    SWIMMING("Natacion"),
    BOXING("Boxeo"),
    ATHLETICS("Atletismo"),
    GOLF("Golf"),
    CRICKET("Criquet"),
    BASEBALL("Beisbol"),
    RUGBY("Rugby"),
    VOLLEYBALL("Voleibol"),
    HANDBALL("Balonmano"),
    CYCLING("Ciclismo"),
    HOCKEY("Hockey"),
    SKIING("Esqui"),
    SNOWBOARDING("Snowboard"),
    ICE_HOCKEY("Hockey sobre hielo"),
    SURFING("Surf"),
    BADMINTON("Badminton"),
    TABLE_TENNIS("Tenis de mesa"),
    WRESTLING("Lucha"),
    JUDO("Judo"),
    KARATE("Karate"),
    TAEKWONDO("Taekwondo"),
    FENCING("Esgrima"),
    ARCHERY("Tiro con arco"),
    SHOOTING("Tiro"),
    EQUESTRIAN("Equitacion"),
    TRIATHLON("Triatlon"),
    WEIGHTLIFTING("Halterofilia"),
    SAILING("Vela"),
    ROWING("Remo"),
    CANOEING("Piragüismo"),
    SKATEBOARDING("Monopatín"),
    CLIMBING("Escalada"),
    GYMNASTICS("Gimnasia"),
    MARTIAL_ARTS("Artes marciales"),
    POLO("Polo"),
    MOTORSPORTS("Deportes de motor"),
    ESPORTS("Esports");

    private final String name;

    Sport(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
