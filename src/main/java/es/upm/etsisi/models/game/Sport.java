package es.upm.etsisi.models.game;

public enum Sport {
    SOCCER("Fútbol"),
    BASKETBALL("Baloncesto"),
    TENNIS("Tenis"),
    SWIMMING("Natación"),
    BOXING("Boxeo"),
    ATHLETICS("Atletismo"),
    GOLF("Golf"),
    CRICKET("Críquet"),
    BASEBALL("Béisbol"),
    RUGBY("Rugby"),
    VOLLEYBALL("Voleibol"),
    HANDBALL("Balonmano"),
    CYCLING("Ciclismo"),
    HOCKEY("Hockey"),
    SKIING("Esquí"),
    SNOWBOARDING("Snowboard"),
    ICE_HOCKEY("Hockey sobre hielo"),
    SURFING("Surf"),
    BADMINTON("Bádminton"),
    TABLE_TENNIS("Tenis de mesa"),
    WRESTLING("Lucha"),
    JUDO("Judo"),
    KARATE("Karate"),
    TAEKWONDO("Taekwondo"),
    FENCING("Esgrima"),
    ARCHERY("Tiro con arco"),
    SHOOTING("Tiro"),
    EQUESTRIAN("Equitación"),
    TRIATHLON("Triatlón"),
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
