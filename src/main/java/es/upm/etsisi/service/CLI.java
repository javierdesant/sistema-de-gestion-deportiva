package es.upm.etsisi.service;

import es.upm.etsisi.utils.Status;
import es.upm.etsisi.views.ErrorView;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;
import es.upm.etsisi.models.User;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class CLI {
    private final Controller controller;
    private final CommandFactory commandFactory;
    private final Scanner scanner;  // TODO: replace with console
    private Status status;

    public CLI() {
        this.controller = new Controller();
        this.commandFactory = new CommandFactory(this.controller);
        this.scanner = new Scanner(System.in);
        this.status = Status.OPEN;
    }

    public boolean isOpen() {
        return this.status == Status.OPEN;
    }

    public void close() {
        assert this.isOpen();
        this.status = Status.CLOSED;
    }

    public void run() {
        ErrorType error;

        do {
            User user = this.controller.getUser();
            String input = this.readInput();
            String[] split = input.trim().split(" ", 2);
            String title = split[0];
            String[] arguments = split.length > 1 ? split[1].split(";") : new String[0];

            Command command = this.commandFactory.getCommand(user != null ? user.getRole() : null, title);
            if (command != null) {
                error = command.execute(arguments);
            } else {
                error = ErrorType.INVALID_COMMAND;
            }

            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (this.isOpen());
    }

    private String readInput() {
        User user = this.controller.getUser();

        System.out.println();
        System.out.print(user != null ? user + " # " : "");
        Message.COMMAND_PROMPT.write();

        return this.scanner.nextLine();
    }
}
