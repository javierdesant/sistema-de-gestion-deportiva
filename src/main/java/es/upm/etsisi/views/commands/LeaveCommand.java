package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class LeaveCommand extends Command {
    private final Controller controller;

    LeaveCommand(Controller controller) {
        super("tournament-remove", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TODO
        return null;
    }
}
