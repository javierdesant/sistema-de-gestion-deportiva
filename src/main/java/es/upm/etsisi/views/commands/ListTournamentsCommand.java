package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentManager;

public class ListTournamentsCommand extends Command {
    private final TournamentManager tournamentService;

    ListTournamentsCommand(TournamentManager tournamentManager) {
        super("tournaments", 0, "Lista los torneos del sistema.");
        this.tournamentService = tournamentManager;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TournamentList tournaments = this.controller.getTournaments();

        // TODO
        return ErrorType.NULL;
    }
}
