package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;

import java.util.LinkedList;

public class MatchmakeCommand extends Command {
    private final TournamentService tournamentService;

    MatchmakeCommand(TournamentService tournamentService) {
        super("tournament-matchmaking", 4,
                "[-m;tournamentName;dni_1;dni_2] [-a;tournamentName;groupSize] Genera emparejamientos para el torneo especificado, permitiendo emparejamiento manual (-m) o automático aleatorio (-a).");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error = ErrorType.NULL;
        String tournamentName = args.pollToken();

        if (this.areInvalidTokens(tournamentName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }
        if (args.containsFlag("-m")) {
            LinkedList<DNI> players = new LinkedList<>();
            error = this.pollPlayers(args, players);
            if (!error.isNull()) {
                return error;
            }
            error = this.tournamentService.tournamentMatchmake(tournamentName, players);

            if (error.isNull()) {
                CommandFeedback.MATCH_ADDED.writeln();
            }
        } else if (args.containsFlag("-a")) {
            String groupSize = args.pollToken();
            error = this.tournamentService.tournamentRandomMatchmake(tournamentName, Integer.valueOf(groupSize));

            if (error.isNull()) {
                CommandFeedback.MATCHES_RANDOMIZED.writeln();
            }
        } else {
            error = ErrorType.INVALID_ARGUMENTS;
        }
        return error;
    }

    private ErrorType pollPlayers(ParsedInput args, LinkedList<DNI> players) {
        ErrorType error = ErrorType.NULL;
        while (args.hasToken() && error.isNull()) {
            String token = args.pollToken();
            if (this.areInvalidTokens(token)) {
                error = ErrorType.INVALID_ARGUMENTS;
            } else if (!DNI.isValidDNI(token)) {
                error = ErrorType.INVALID_DNI_ERROR;
            }
            players.add(DNI.valueOf(token));
        }
        return error;
    }
}