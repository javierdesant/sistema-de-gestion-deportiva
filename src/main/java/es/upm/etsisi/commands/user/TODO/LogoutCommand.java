package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;
import es.upm.etsisi.service.CLI;

public class LogoutCommand extends Command {
    private final AuthController authController;
    private final CLI cli;

    public LogoutCommand(AuthController authController, CLI cli) {
        super("logout");
        this.authController = authController;
        this.cli = cli;
    }

    @Override
    public void execute() {
        this.authController.logout();
        this.cli.updateCommands();
    }
}
