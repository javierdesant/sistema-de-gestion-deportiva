package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.Message;

public class EnrollCommand extends Command {
    private final TournamentService tournamentService;

    EnrollCommand(TournamentService tournamentService) {
        super("tournament-add", 2, "[tournament] Inscribe al jugador autenticado o a su equipo en un torneo.");
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
            error = this.tournamentService.enrollTeamOfUser(tournamentName);
        } else {
            error = this.tournamentService.enrollUser(tournamentName);
        }

        if (error.isNull()) {
            Message.PLAYER_ADDED.writeln();
        }
        return error;
    }
}
