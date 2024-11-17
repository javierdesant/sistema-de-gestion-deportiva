package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class LeaveCommand extends Command {
    private final Controller controller;

    public LeaveCommand(Controller controller) {
        super("tournament-remove", 0);
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
