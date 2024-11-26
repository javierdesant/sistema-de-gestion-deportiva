package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

import java.util.LinkedList;

public class MatchmakeCommand extends Command {
    private final Controller controller;

    MatchmakeCommand(Controller controller) {
        super("tournament-matchmaking", 10, "Genera emparejamientos para el torneo especificado, permitiendo emparejamiento manual (-m) o automático aleatorio (-a).");
        // TODO: revisar el numero de argumentos para match ? grupos ?
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error = ErrorType.NULL;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        LinkedList<String> players = new LinkedList<>();
        while (args.hasToken() && error.isNull()) {
            String token = args.pollToken();
            if (this.areInvalidTokens(token)) {
                error = ErrorType.INVALID_ARGUMENTS;
            }
            players.add(token);
        }

        if (!error.isNull()) {
            return error;
        }

        error = this.controller.tournamentMatchmake(tournamentName, players);

        if (!error.isNull()) {
            // TODO: add message
        }
        return error;
    }
}