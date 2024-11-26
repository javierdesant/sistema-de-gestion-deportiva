package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class CreateTournamentCommand extends Command {  // TODO: finish pending Tournament model
    private final Controller controller;

    CreateTournamentCommand(Controller controller) {
        super("tournament-create", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {

        // TODO
        return null;
    }
}
