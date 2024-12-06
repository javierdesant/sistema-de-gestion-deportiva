package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;

public class EnrollCommand extends Command {
    private final TournamentService tournamentService;

    EnrollCommand(TournamentService tournamentService) {
        super("tournament-add", 2, "[[-t];tournament] Inscribe al jugador autenticado o a su equipo (-t) en un torneo.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        if (args.containsFlag("-t")) {
            error = this.tournamentService.enrollTeamOfUser(tournamentName);
            if (error.isNull()) {
                CommandFeedback.TEAM_ENROLLED.writeln();
            }
        } else {
            error = this.tournamentService.enrollUser(tournamentName);
            if (error.isNull()) {
                CommandFeedback.PLAYER_ENROLLED.writeln();
            }
        }

        return error;
    }
}
