package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

import java.util.LinkedList;

public class MatchmakeCommand extends Command {
    private final Controller controller;

    public MatchmakeCommand(Controller controller) {
        super("matchmake", 10);      // TODO: revisar el numero de argumentos para match ? grupos ?
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error = null;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        LinkedList<String> players = new LinkedList<>();
        while (args.hasToken() && error == null) {
            String token = args.pollToken();
            if (this.areInvalidTokens(token)) {
                error = ErrorType.INVALID_ARGUMENTS;
            }
            players.add(token);
        }

        if (error != null) {
            return error;
        }

        error = this.controller.tournamentMatchmake(tournamentName, players);

        if (error != null) {
            // TODO: add message
        }
        return error;
    }
}