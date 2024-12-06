package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.CommandFeedback;

public class CreateTeamCommand extends Command {
    private final ParticipantService participantService;

    CreateTeamCommand(ParticipantService participantService) {
        super("team-create", 2, "[new-team;player] Añade a un jugador a un nuevo equipo.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String teamName = args.pollToken();
        String playerDni = args.pollToken();

        if (this.areInvalidTokens(teamName, playerDni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!teamName.matches("[a-zA-Z]+")) {
            return ErrorType.NAME_FORMAT_ERROR;
        }
        if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.participantService.createTeam(teamName, DNI.valueOf(playerDni));

        if (error.isNull()) {
            CommandFeedback.TEAM_ADDED.writeln();
        }
        return error;
    }
}
