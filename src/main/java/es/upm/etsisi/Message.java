package es.upm.etsisi;

public enum Message {
    COMMAND_PROMPT("> "),
    INVALID_COMMAND("Comando no válido. (Escriba h o help para ver el listado de comandos)"),
    INVALID_ARGUMENTS("Argumentos no válidos"),
    INVALID_NAME("Nombre no válido"),
    INVALID_NUMBER("Introduzca un número válido"),
    MATCHES_RANDOMIZED("Emparejamiento aleatorio realizado con éxito."),
    PLAYER_ADDED("Jugador añadido con éxito."),
    PLAYER_REMOVED("Jugador eliminado con éxito."),
    PLAYERS_MATCHED("Los jugadores #homeplayer y #visitingplayer han sido emparejados correctamente."),
    NO_MATCHES("No hay emparejamientos"),
    NO_PLAYERS("No hay jugadores"),
    NULL_PLAYERLIST("La lista de jugadores no puede ser nula"),
    NULL_MATCHLIST("La lista de emparejamientos no puede ser nula"),
    EVEN_PLAYERS_REQUIRED("El número de jugadores debe ser par"),
    PLAYERLIST_HEADER("------ LISTA DE JUGADORES ------"),
    RANKING_HEADER("---------- RANKING -----------"),
    MATCHMAKE_HEADER("------ EMPAREJAMIENTOS -------"),
    FOOTER("------------------------------"),
    MATCHMAKE_CLEARED("Los emparejamientos han sido eliminados."),
    RANDOM_MATCHMAKE_WARNING("Esta opción creará emparejamientos aleatorios con todos los jugadores, eliminado los emparejamientos anteriores."),
    ERASE_MATCHED_PLAYER_WARNING("El jugador que intenta borrar se encuentra emparejado. Borrar a este jugador supone también borrar su emparejamiento."),
    CONTINUE_PROMPT("¿Desea continuar? (S/N) "),
    CANCEL("Cancelando..."),
    INVALID_OPTION("Opción no válida."),
    HOME_PLAYER_NOT_EXIST("El jugador local no existe"),
    VISITING_PLAYER_NOT_EXIST("El jugador visitante no existe"),
    SCORE_OUT_OF_BOUNDS_ERROR("La puntuación supera los límites"),
    SCORE_UPDATED("La puntuación de %s ahora es %.2f."),
    SAME_PLAYER_ERROR("Los jugadores no pueden ser el mismo"),
    PLAYERS_MATCHED_ERROR("Los jugadores deben estar sin emparejar"),
    PLAYER_ALREADY_EXISTS_ERROR("El jugador ya existe"),
    PLAYER_DOES_NOT_EXIST_ERROR("El jugador no existe"),
    HELP_MESSAGE(
            "Comandos disponibles:\n" +
                    "  help                             - Muestra este mensaje\n" +
                    "  create [player]                  - Crea un nuevo jugador\n" +
                    "  remove [player]                  - Elimina un jugador\n" +
                    "  show                             - Muestra la lista de jugadores\n" +
                    "  rank                             - Muestra el ranking de jugadores\n" +
                    "  score [player];[score]           - Asigna una puntuación a un jugador\n" +
                    "  show_matchmake                   - Muestra el estado actual de las partidas\n" +
                    "  clear_matchmake                  - Limpia el estado de las partidas\n" +
                    "  matchmake [player1];[player2]    - Inicia una partida entre dos jugadores\n" +
                    "  random_matchmake                 - Inicia partidas aleatorias entre todos los jugadores\n" +
                    "  exit                             - Cierra el programa"
    ),
    EXIT_MESSAGE("Cerrando...");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public void write() {
        System.out.print(this.message);
    }

    public void writeln() {
        System.out.println(this.message);
    }

    public void writeln(String playerName, double score) {
        System.out.printf((this.message) + "%n", playerName, score);
    }

    public void write(String playerName, double score) {
        System.out.printf(this.message, playerName, score);
    }

    public void writeln(String homePlayerName, String visitingPlayerName) {
        assert this == Message.PLAYERS_MATCHED;

        System.out.println(this.message.replaceAll("#homeplayer", homePlayerName).replaceAll("#visitingplayer", visitingPlayerName));
    }

    @Override
    public String toString() {
        return message;
    }
}
