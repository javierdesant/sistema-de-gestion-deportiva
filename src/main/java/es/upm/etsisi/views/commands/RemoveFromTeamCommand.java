package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantManager;
import es.upm.etsisi.utils.Message;

public class RemoveFromTeamCommand extends Command {
    private final ParticipantManager participantManager;

    RemoveFromTeamCommand(ParticipantManager participantManager) {
        super("team-remove", 2, "[team;dni] Elimina al jugador de un equipo.");
        this.participantManager = participantManager;
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

        error = this.participantManager.removeFromTeam(teamName, DNI.valueOf(playerDni));

        if (error.isNull()) {
            Message.PLAYER_REMOVED_FROM_TEAM.writeln(playerDni, teamName);
        }
        return error;
    }
}
