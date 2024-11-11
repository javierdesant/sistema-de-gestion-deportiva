package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.Controller;

import java.util.Scanner;

public class RegisterCommand extends Command {
    private final Controller controller;
    Scanner scanner;

    public RegisterCommand(Controller controller) {
        super("register", 2);
        this.controller = controller;
        this.scanner = new Scanner(System.in);  // FIXME: resources leak
    }

    @Override
    public void execute() {
        String username = this.getArgument(0);
        String password = this.getArgument(1);

        this.controller.register(username, password);
    }
}
