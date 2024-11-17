package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class RemoveFromTeamCommand extends Command {
    private final Controller controller;

    public RemoveFromTeamCommand(Controller controller) {
        super("team-remove", 2);
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
