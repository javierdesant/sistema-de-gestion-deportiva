package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;
import es.upm.etsisi.service.CLI;

public class LoginCommand extends Command {
    private final AuthController authController;
    private final CLI cli;

    public LoginCommand(AuthController authController, CLI cli) {
        super("login");
        this.authController = authController;
        this.cli = cli;
    }

    @Override
    public void execute() {
        String username = getArgument(0);
        String password = getArgument(1);

        boolean success = this.authController.login(username, password);

        assert success : "login error"; // TODO: add message

        this.cli.updateCommands();
    }
}
