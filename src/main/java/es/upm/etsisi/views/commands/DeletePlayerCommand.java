package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.CommandFeedback;

public class DeletePlayerCommand extends Command {
    private final ParticipantService participantService;

    DeletePlayerCommand(ParticipantService participantService) {
        super("player-delete", 1,
                "[dni] Elimina del sistema a un jugador, siempre que no participe en un torneo en curso ni pertenezca a un equipo que esté participando en uno.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String playerDni = args.pollToken();

        if (this.areInvalidTokens(playerDni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!DNI.isValidDNI(playerDni)) {
            return ErrorType.INVALID_DNI_ERROR;
        }

        error = this.participantService.deletePlayer(DNI.valueOf(playerDni));

        if (error.isNull()) {
            CommandFeedback.PLAYER_REMOVED.writeln();
        }
        return error;
    }
}
