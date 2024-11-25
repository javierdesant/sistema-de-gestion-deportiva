package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class DeleteTournamentCommand extends Command {
    private final Controller controller;

    public DeleteTournamentCommand(Controller controller) {
        super("tournament-delete", 1);
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

        if (error == null) {
            // TODO: add message
        }
        return error;
    }
}
