package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.CommandFeedback;

public class RemoveFromTeamCommand extends Command {
    private final ParticipantService participantService;

    RemoveFromTeamCommand(ParticipantService participantService) {
        super("team-remove", 2, "[team;dni] Elimina al jugador de un equipo.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();
        String playerDni = args.pollToken();

        if (this.areInvalidTokens(teamName, playerDni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.participantService.removeFromTeam(teamName, DNI.valueOf(playerDni));

        if (error.isNull()) {
            CommandFeedback.PLAYER_REMOVED_FROM_TEAM.writeln(playerDni, teamName);
        }
        return error;
    }
}
