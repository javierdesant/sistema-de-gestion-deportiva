package es.upm.etsisi.commands;

import es.upm.etsisi.models.TournamentList;
import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class ListTournamentsCommand extends Command {
    private final Controller controller;

    public ListTournamentsCommand(Controller controller) {
        super("tournaments", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        TournamentList tournaments = this.controller.getTournaments();

        // TODO
        return null;
    }
}
