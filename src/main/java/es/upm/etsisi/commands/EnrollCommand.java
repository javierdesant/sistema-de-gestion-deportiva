package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class EnrollCommand extends Command {
    private final Controller controller;

    public EnrollCommand(Controller controller) {
        super("tournament-add", 0);
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
