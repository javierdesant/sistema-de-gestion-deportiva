package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;

public class DeleteTournamentCommand extends Command {
    private final TournamentService tournamentService;

    DeleteTournamentCommand(TournamentService tournamentService) {
        super("tournament-delete", 1, "[tournament] Elimina un torneo del sistema, incluso si está en curso.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.tournamentService.deleteTournament(tournamentName);

        if (error.isNull()) {
            // TODO: add message
        }
        return error;
    }
}
