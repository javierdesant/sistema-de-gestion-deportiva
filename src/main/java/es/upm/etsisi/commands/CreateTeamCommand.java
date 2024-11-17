package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class CreateTeamCommand extends Command {
    private final Controller controller;

    public CreateTeamCommand(Controller controller) {
        super("team-create", 1);
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
