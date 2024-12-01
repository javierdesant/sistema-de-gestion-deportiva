package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class AddToTeamCommand extends Command {
    private final Controller controller;

    AddToTeamCommand(Controller controller) {
        super("team-add", 2, "[dni;team] Añade al jugador indicado a un equipo.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String playerDni = args.pollToken();
        String teamName = args.pollToken();

        if (this.areInvalidTokens(playerDni, teamName)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.controller.addToTeam(DNI.valueOf(playerDni), teamName);

        if (error.isNull()) {
            Message.PLAYER_ADDED_TO_TEAM.writeln(playerDni, teamName);
        }
        return error;
    }
}
