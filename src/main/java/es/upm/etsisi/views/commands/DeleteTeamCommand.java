package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.Message;

public class DeleteTeamCommand extends Command {
    private final ParticipantService participantService;

    DeleteTeamCommand(ParticipantService participantService) {
        super("team-delete", 1,
                "[team] Elimina del sistema al equipo, siempre que no esté participando en un torneo en curso.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();

        if (this.areInvalidTokens(teamName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.participantService.deleteTeam(teamName);

        if (error.isNull()) {
            Message.TEAM_REMOVED.writeln();
        }
        return error;
    }
}
