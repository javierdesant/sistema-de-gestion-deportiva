package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.CommandFeedback;

public class AddToTeamCommand extends Command {
    private final ParticipantService participantService;

    AddToTeamCommand(ParticipantService participantService) {
        super("team-add", 2, "[dni;team] Añade al jugador indicado a un equipo.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String playerDni = args.pollToken();
        String teamName = args.pollToken();

        if (this.areInvalidTokens(playerDni, teamName)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.participantService.addToTeam(DNI.valueOf(playerDni), teamName);

        if (error.isNull()) {
            CommandFeedback.PLAYER_ADDED_TO_TEAM.writeln(playerDni, teamName);
        }
        return error;
    }
}
