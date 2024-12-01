package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class CreateTeamCommand extends Command {
    private final Controller controller;

    CreateTeamCommand(Controller controller) {
        super("team-create", 2, "[new-team;player] Añade a un jugador a un nuevo equipo.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();
        String playerDni = args.pollToken();

        if (this.areInvalidTokens(teamName, playerDni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!teamName.matches("[a-zA-Z]+")) {
            return ErrorType.NAME_FORMAT_ERROR;
        } if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.controller.createTeam(teamName, DNI.valueOf(playerDni));

        if (error.isNull()) {
            Message.TEAM_ADDED.writeln();
        }
        return error;
    }
}
