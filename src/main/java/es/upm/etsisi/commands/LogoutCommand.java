package es.upm.etsisi.commands;

import es.upm.etsisi.service.CLI;
import es.upm.etsisi.service.Controller;

public class LogoutCommand extends Command {
    private final Controller controller;
    private final CLI cli;

    public LogoutCommand(Controller controller, CLI cli) {
        super("logout", 0);
        this.controller = controller;
        this.cli = cli;
    }

    @Override
    public void execute() {
        this.controller.logout();
        this.cli.updateCommands();
    }
}