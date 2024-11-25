package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class LogoutCommand extends Command {
    private final Controller controller;

    LogoutCommand(Controller controller) {
        super("logout", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        this.controller.logout();

        return null;
    }
}
