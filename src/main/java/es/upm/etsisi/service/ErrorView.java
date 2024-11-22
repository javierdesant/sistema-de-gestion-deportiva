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
