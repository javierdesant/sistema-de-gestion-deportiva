package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;
import es.upm.etsisi.service.CLI;

public class LoginCommand extends Command {
    private final AuthController authController;
    private final CLI CLI;

    public LoginCommand(AuthController authController, CLI CLI) {
        super("login");
        this.authController = authController;
        this.CLI = CLI;
    }

    @Override
    public void execute() {
        String username = getArgument(0);
        String password = getArgument(1);

        boolean success = this.authController.login(username, password);

        assert success; // TODO: add message

        this.CLI.updateCommands();
    }
}
