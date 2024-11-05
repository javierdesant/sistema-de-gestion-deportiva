package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;

import java.util.Scanner;

public class RegisterCommand extends Command {
    private final AuthController authController;
    Scanner scanner;

    public RegisterCommand(AuthController authController) {
        super("register", 2);
        this.authController = authController;
        this.scanner = new Scanner(System.in);  // FIXME: resources leak
    }

    @Override
    public void execute() {
        String username = this.getArgument(0);
        String password = this.getArgument(1);

        this.authController.register(username, password);
    }
}
