package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;

import java.util.LinkedList;

public class MatchmakeCommand extends Command {
    private final TournamentService tournamentService;

    MatchmakeCommand(TournamentService tournamentService) {
        super("tournament-matchmaking", 10,
                "[-m/-a;tournament;[dni...]] Genera emparejamientos para el torneo especificado, permitiendo emparejamiento manual (-m) o automático aleatorio (-a).");
        // TODO: revisar el numero de argumentos para match ? grupos ?
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) { // TODO
        ErrorType error = ErrorType.NULL;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        LinkedList<DNI> players = new LinkedList<>();
        while (args.hasToken() && error.isNull()) {
            String token = args.pollToken();
            if (this.areInvalidTokens(token)) {
                error = ErrorType.INVALID_ARGUMENTS;
            } else if (!DNI.isValidDNI(token)) {
                error = ErrorType.INVALID_DNI_ERROR;
            }
            players.add(DNI.valueOf(token));
        }

        if (!error.isNull()) {
            return error;
        }

        error = this.tournamentService.tournamentMatchmake(tournamentName, players);

        if (!error.isNull()) {
            // TODO: add message
        }
        return error;
    }
}