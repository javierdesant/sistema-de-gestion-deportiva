package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class CreateTournamentCommand extends Command {  // TODO: finish pending Tournament model
    private final Controller controller;

    public CreateTournamentCommand(Controller controller) {
        super("createTournament", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TODO
        return null;
    }
}
