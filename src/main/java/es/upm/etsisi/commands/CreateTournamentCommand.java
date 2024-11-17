package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class CreateTournamentCommand extends Command {  // TODO: finish pending Tournament model
    private final Controller controller;

    public CreateTournamentCommand() {
        super("createTournament", 0);
        controller = new Controller();
    }

    @Override
    public void execute() {
        // TODO
    }
}
