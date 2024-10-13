package es.upm.etsisi;

public enum Message {
    COMMAND_PROMPT("> "),
    INVALID_COMMAND("Comando no válido. (Escriba h o help para ver el listado de comandos)"),
    INVALID_ARGUMENTS("Argumentos no válidos"),
    INVALID_NAME("Nombre no válido"),
    INVALID_NUMBER("Introduzca un número válido"),
    PLAYER_ADDED("Jugador añadido con éxito."),
    PLAYER_REMOVED("Jugador eliminado con éxito."),
    NO_PLAYERS("No hay jugadores"),
    NULL_PLAYERLIST("La lista de jugadores no puede ser nula"),
    NULL_MATCHLIST("La lista de emparejamientos no puede ser nula"),
    PLAYERLIST_HEADER("------ LISTA DE JUGADORES ------"),
    RANKING_HEADER("---------- RANKING -----------"),
    MATCHMAKE_HEADER("------ EMPAREJAMIENTOS -------"),
    FOOTER("------------------------------"),
    MATCHMAKE_CLEARED("Los emparejamientos han sido eliminados."),
    RANDOM_MATCHMAKE_WARNING("Esta opción creará emparejamientos aleatorios con todos los jugadores, eliminado los emparejamientos anteriores."),
    CONTINUE_PROMPT("¿Desea continuar? (S/N) "),
    CANCEL("Cancelando..."),
    INVALID_OPTION("Opción no válida."),
    HOME_PLAYER_NOT_EXIST("El jugador local no existe"),
    VISITING_PLAYER_NOT_EXIST("El jugador visitante no existe"),
    SAME_PLAYER_ERROR("Los jugadores no pueden ser el mismo"),
    HELP_MESSAGE(
            "Comandos disponibles:\n" +
                    "  help                             - Muestra los comandos disponibles\n" +
                    "  create [player]                  - Crea un nuevo jugador\n" +
                    "  remove [player]                  - Elimina un jugador\n" +
                    "  show                             - Muestra la lista de jugadores\n" +
                    "  rank                             - Muestra el ranking de jugadores\n" +
                    "  score [player];[score]           - Asigna una puntuación a un jugador\n" +
                    "  show_matchmake                   - Muestra el estado actual de las partidas\n" +
                    "  clear_matchmake                  - Limpia el estado de las partidas\n" +
                    "  matchmake [player1];[player2]    - Inicia una partida entre dos jugadores\n" +
                    "  random_matchmake                 - Inicia una partida aleatoria entre jugadores\n" +
                    "  exit                             - Salir del programa"
    ),
    EXIT_MESSAGE("Cerrando...");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    void write() {
        System.out.print(this.message);
    }

    void writeln() {
        System.out.println(this.message);
    }

    @Override
    public String toString() {
        return message;
    }
}
