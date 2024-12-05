package es.upm.etsisi.utils;

public enum CommandFeedback {
    COMMAND_PROMPT("> "),
    USER_LOGGED_IN("Sesión iniciada: #user "),
    PLAYER_ADDED("Jugador añadido con éxito."),
    PLAYER_REMOVED("Jugador eliminado con éxito."),
    TEAM_ADDED("Equipo añadido con éxito"),
    TEAM_REMOVED("Equipo eliminado con éxito"),
    PLAYER_ADDED_TO_TEAM("Jugador #player añadido al equipo #team con éxito"),
    PLAYER_REMOVED_FROM_TEAM("Jugador #player borrado del equipo #team con éxito"),
    TOURNAMENT_ADDED("Torneo creado con éxito"),
    TOURNAMENT_REMOVED("Torneo eliminado con éxito"),
    PLAYER_ENROLLED("Jugador inscrito al torneo con éxito"),
    PLAYER_DELISTED("Baja de torneo realizada con éxito"),
    TEAM_ENROLLED("Equipo inscrito al torneo con éxito"),
    TEAM_DELISTED("Baja de equipo de torneo realizada con éxito"),
    MATCH_ADDED("Emparejamiento manual realizado con éxito"),
    MATCHES_RANDOMIZED("Emparejamiento aleatorio realizado con éxito."),
    EXIT_MESSAGE("Cerrando...");

    private final String message;

    CommandFeedback(String message) {
        this.message = message;
    }

    public void write() {
        System.out.print(this.message);
    }

    public void writeln() {
        System.out.println(this.message);
    }

    public void writeln(String player, String team) {
        System.out.println(this.message.replaceAll("#player", player).replaceAll("#team", team));
    }

    public void writeln(String user) {
        System.out.println(this.message.replaceAll("#user", user));
    }

    @Override
    public String toString() {
        return message;
    }
}
