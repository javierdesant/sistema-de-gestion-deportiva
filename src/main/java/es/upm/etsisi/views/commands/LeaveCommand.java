package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;

public class LeaveCommand extends Command {
    private final TournamentService tournamentService;

    LeaveCommand(TournamentService tournamentService) {
        super("tournament-remove", 2,
                "[[-t],tournament] Da de baja al jugador autenticado o a su equipo de un torneo.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        if (args.containsFlag("-t")) {
            error = this.tournamentService.leaveTournamentAsTeam(tournamentName);
            if (error.isNull()) {
                CommandFeedback.TEAM_DELISTED.writeln();
            }
        } else {
            error = this.tournamentService.leaveTournament(tournamentName);
            if (error.isNull()) {
                CommandFeedback.PLAYER_DELISTED.writeln();
            }
        }

        return error;
    }
}
