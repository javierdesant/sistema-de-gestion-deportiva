package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class DeleteTeamCommand extends Command {
    private final Controller controller;

    public DeleteTeamCommand(Controller controller) {
        super("team-delete", 0);        // TODO: define Tournament and Match models
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
