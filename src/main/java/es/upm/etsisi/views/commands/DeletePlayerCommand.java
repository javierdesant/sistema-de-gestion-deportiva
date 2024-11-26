package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class DeletePlayerCommand extends Command {
    private final Controller controller;

    DeletePlayerCommand(Controller controller) {
        super("player-remove", 1, "Elimina del sistema a un jugador, siempre que no participe en un torneo en curso ni pertenezca a un equipo que esté participando en uno.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String playerName = args.pollToken();

        if (this.areInvalidTokens(playerName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.deletePlayer(playerName);

        if (error.isNull()) {
            Message.PLAYER_REMOVED.writeln();
        }
        return error;
    }
}
