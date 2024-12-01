package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class LeaveCommand extends Command {
    private final Controller controller;

    LeaveCommand(Controller controller) {
        super("tournament-remove", 2, "[[-t],tournament] Da de baja al jugador autenticado o a su equipo de un torneo.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        if (args.containsFlag("-t")) {
            error = this.controller.leaveTournamentAsTeam(tournamentName);
        } else {
            error = this.controller.leaveTournament(tournamentName);
        }

        if (error.isNull()) {
            Message.PLAYER_ADDED.writeln();
        }
        return error;
    }
}
