package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;
import es.upm.etsisi.service.CLI;

public class LogoutCommand extends Command {
    private final AuthController authController;
    private final CLI CLI;

    public LogoutCommand(AuthController authController, CLI CLI) {
        super("logout");
        this.authController = authController;
        this.CLI = CLI;
    }

    @Override
    public void execute() {
        this.authController.logout();
        this.CLI.updateCommands();
    }
}
