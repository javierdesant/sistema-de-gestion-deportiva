package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class DeleteTournamentCommand extends Command {
    private final Controller controller;

    DeleteTournamentCommand(Controller controller) {
        super("tournament-delete", 1, "[tournament] Elimina un torneo del sistema, incluso si está en curso.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.deleteTournament(tournamentName);

        if (error.isNull()) {
            // TODO: add message
        }
        return error;
    }
}
