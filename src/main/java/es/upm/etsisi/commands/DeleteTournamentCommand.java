package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class DeleteTournamentCommand extends Command {
    private final Controller controller;

    public DeleteTournamentCommand(Controller controller) {
        super("tournament-delete", 0);  // TODO: define Tournament and Match models
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
