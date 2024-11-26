package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.TournamentList;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class ListTournamentsCommand extends Command {
    private final Controller controller;

    ListTournamentsCommand(Controller controller) {
        super("tournaments", 0, "Lista los torneos del sistema.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        TournamentList tournaments = this.controller.getTournaments();

        // TODO
        return ErrorType.NULL;
    }
}
