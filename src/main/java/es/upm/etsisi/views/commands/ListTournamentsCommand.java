package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentManager;

public class ListTournamentsCommand extends Command {
    private final TournamentManager tournamentService;

    ListTournamentsCommand(TournamentManager tournamentManager) {
        super("tournament-list", 0, "Lista los torneos del sistema.");
        this.tournamentService = tournamentManager;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        this.tournamentService.listTournament();

        return ErrorType.NULL;
    }
}
