package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class LogoutCommand extends Command {
    private final Controller controller;

    public LogoutCommand(Controller controller) {
        super("logout", 0);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        this.controller.logout();

        return null;
    }
}
