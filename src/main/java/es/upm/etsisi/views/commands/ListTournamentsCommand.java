package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;

public class ListTournamentsCommand extends Command {
    private final TournamentService tournamentService;

    ListTournamentsCommand(TournamentService tournamentService) {
        super("tournament-list", 0, "Lista los torneos del sistema.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        return this.tournamentService.listTournaments();
    }
}
