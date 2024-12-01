package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class DeletePlayerCommand extends Command {
    private final Controller controller;

    DeletePlayerCommand(Controller controller) {
        super("player-remove", 1, "[dni] Elimina del sistema a un jugador, siempre que no participe en un torneo en curso ni pertenezca a un equipo que esté participando en uno.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String playerDni = args.pollToken();

        if (this.areInvalidTokens(playerDni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.controller.deletePlayer(DNI.valueOf(playerDni));

        if (error.isNull()) {
            Message.PLAYER_REMOVED.writeln();
        }
        return error;
    }
}
