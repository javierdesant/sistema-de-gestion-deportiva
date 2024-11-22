package es.upm.etsisi.service;

import java.util.EnumMap;

public class ErrorView {
    private final Console console;
    private final Error error;

    private static final EnumMap<Error, String> MESSAGES = new EnumMap<>(Error.class);

    static {
        MESSAGES.put(Error.DUPLICATE_ELEMENT_ERROR, "El elemento ya existe en la lista");
        MESSAGES.put(Error.DUPLICATE_TOURNAMENT_ERROR, "El torneo ya existe en la lista");
        MESSAGES.put(Error.DUPLICATE_MATCH_ERROR, "El partido ya existe en la lista");
        MESSAGES.put(Error.PLAYER_ALREADY_IN_MATCH_ERROR, "El jugador ya está asignado a un partido");
        MESSAGES.put(Error.ELEMENT_NOT_FOUND, "El elemento no se encuentra en la lista");
        MESSAGES.put(Error.NAME_FORMAT_ERROR, "El nombre sólo puede contener letras");
        MESSAGES.put(Error.PLAYER_NOT_IN_TEAM, "El jugador no pertenece al equipo");
        MESSAGES.put(Error.USER_NOT_FOUND, "El usuario no existe");
        MESSAGES.put(Error.PLAYER_NOT_FOUND, "Jugador no encontrado");
        MESSAGES.put(Error.TEAM_NOT_FOUND, "Equipo no encontrado");
        MESSAGES.put(Error.WRONG_PASSWORD, "Contraseña incorrecta");
        MESSAGES.put(Error.PLAYER_IN_GAME_ERROR, "El jugador se encuentra en un torneo activo");
        MESSAGES.put(Error.INVALID_COMMAND, "Comando no válido");
    }

    public ErrorView(Error error) {
        this.console = new Console();
        this.error = error;
    }

    public void writeln() {
        String message = MESSAGES.getOrDefault(this.error, "Error desconocido");
        this.console.writeln(message);
    }
}
