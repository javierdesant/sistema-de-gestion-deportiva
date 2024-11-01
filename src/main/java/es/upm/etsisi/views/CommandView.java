package es.upm.etsisi.views;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.AuthController;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CommandView {
    private final Scanner scanner;
    private final LinkedList<Command> commands;
    private final AuthController authController;

    public CommandView(LinkedList<Command> commands, AuthController authController) {
        this.scanner = new Scanner(System.in);      // FIXME
        this.commands = commands;
        this.authController = authController;
    }

    public void displayError(Error error) {
        System.out.println("Error: " + error.getMessage());
    }

    public Command read() {
        User user = authController.getUser();

        System.out.println();
        System.out.print(user == null ? "" : user + " # ");
        Message.COMMAND_PROMPT.write();

        String input = this.scanner.nextLine().trim();

        Command res = null;
        Iterator<Command> iterator = this.commands.iterator();
        while (iterator.hasNext() && res == null) {
            Command command = iterator.next();
            command.validate(input);
            if (command.isCalled()) {
                res = command;
            }
        }

        if (res == null) {
            Message.INVALID_COMMAND.writeln();
        }

        return res;
    }
}
