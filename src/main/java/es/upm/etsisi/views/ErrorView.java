package es.upm.etsisi.views;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Console;

import java.util.EnumMap;

public class ErrorView {
    private static final EnumMap<ErrorType, String> MESSAGES = new EnumMap<>(ErrorType.class);

    static {
        MESSAGES.put(ErrorType.DUPLICATE_ELEMENT_ERROR, "El elemento ya existe en la lista");
        MESSAGES.put(ErrorType.DUPLICATE_TOURNAMENT_ERROR, "El torneo ya existe en la lista");
        MESSAGES.put(ErrorType.DUPLICATE_MATCH_ERROR, "El partido ya existe en la lista");
        MESSAGES.put(ErrorType.PLAYER_ALREADY_IN_MATCH_ERROR, "Un participante ya está asignado a otro partido o se encuentra duplicado");
        MESSAGES.put(ErrorType.PARTICIPANT_ALREADY_ASSIGNED_ERROR, "El jugador ya está asignado a un partido");
        MESSAGES.put(ErrorType.ELEMENT_NOT_FOUND, "El elemento no se encuentra en la lista");
        MESSAGES.put(ErrorType.PARTICIPANT_NOT_FOUND, "El participante no se encuentra en la lista");
        MESSAGES.put(ErrorType.NAME_FORMAT_ERROR, "El nombre sólo puede contener letras");
        MESSAGES.put(ErrorType.PLAYER_NOT_IN_TEAM, "El jugador no pertenece al equipo");
        MESSAGES.put(ErrorType.USER_NOT_FOUND, "El usuario no existe");
        MESSAGES.put(ErrorType.PLAYER_NOT_FOUND, "Jugador no encontrado");
        MESSAGES.put(ErrorType.TEAM_NOT_FOUND, "Equipo no encontrado");
        MESSAGES.put(ErrorType.TOURNAMENT_NOT_FOUND, "Torneo no encontrado");
        MESSAGES.put(ErrorType.WRONG_PASSWORD, "Contraseña incorrecta");
        MESSAGES.put(ErrorType.INVALID_ARGUMENTS, "Argumentos no válidos");
        MESSAGES.put(ErrorType.PARTICIPANT_NOT_ENROLLED, "El participante no está inscrito en el torneo");
        MESSAGES.put(ErrorType.INVALID_DNI_ERROR, "El DNI no es válido");
        MESSAGES.put(ErrorType.TOURNAMENT_NOT_ACTIVE, "El torneo no se encuentra en curso");
        MESSAGES.put(ErrorType.PLAYER_IN_GAME_ERROR, "El jugador participa en un torneo activo");
        MESSAGES.put(ErrorType.TEAM_IN_GAME_ERROR, "El equipo participa en un torneo activo");
        MESSAGES.put(ErrorType.INVALID_COMMAND, "Comando no válido");
        MESSAGES.put(ErrorType.INVALID_MATCH, "Los partidos deben tener al menos dos jugadores");
    }

    private final Console console;
    private final ErrorType error;

    public ErrorView(ErrorType error) {
        this.console = Console.getInstance();
        this.error = error;
    }

    public void writeln() {
        String message = MESSAGES.getOrDefault(this.error, "Error desconocido");
        this.console.writeln(message);
    }
}
