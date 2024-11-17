package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class DeletePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final Controller controller;

    public DeletePlayerCommand(Controller controller) {
        super("player-remove", 0);
        this.controller = controller;
    }

    @Override
    public void execute() {
        // TODO
    }
}
