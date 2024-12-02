package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;

public class CreateTournamentCommand extends Command { // TODO: finish pending Tournament model
    private final TournamentService tournamentService;

    CreateTournamentCommand(TournamentService tournamentService) { // TODO: add args to description
        super("tournament-create", 0, "Registra un nuevo torneo en el sistema.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {

        // TODO
        return ErrorType.NULL;
    }
}
