package es.upm.etsisi.commands.user;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.CLI;
import es.upm.etsisi.service.Controller;

public class LoginCommand extends Command {
    private final Controller controller;
    private final CLI cli;

    public LoginCommand(Controller controller, CLI cli) {
        super("login", 2);
        this.controller = controller;
        this.cli = cli;
    }

    @Override
    public void execute() {
        String username = getArgument(0);
        String password = getArgument(1);

        boolean success = this.controller.login(username, password);

        assert success : "login error"; // TODO: add message

        this.cli.updateCommands();
    }
}
