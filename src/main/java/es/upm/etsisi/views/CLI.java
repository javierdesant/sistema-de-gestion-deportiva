package es.upm.etsisi.views;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.utils.Status;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;
import es.upm.etsisi.models.User;

import java.util.Arrays;

public class CLI {
    private final Controller controller;
    private final CommandFactory commandFactory;
    private Status status;

    public CLI() {
        this.controller = new Controller();
        this.commandFactory = new CommandFactory(this.controller);
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
            String[] parsedInput = this.splitInput(input);

            Command command = this.commandFactory.getCommand(user != null ? user.getRole() : null, parsedInput[0]);
            if (command != null) {
                error = command.execute(Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
            } else {
                error = ErrorType.INVALID_COMMAND;
            }

            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (this.isOpen());
    }

    private String readInput() {
        Console console = Console.getInstance();
        User user = this.controller.getUser();

        console.writeln();
        console.write(user != null ? user + " " : "");

        return console.readString(Message.COMMAND_PROMPT.toString());
    }

    private String[] splitInput(String input) {
        String[] split = input.trim().split(" ", 2);
        String title = split[0];
        String[] arguments = split.length > 1 ? split[1].split(";") : new String[0];

        String[] result = new String[arguments.length + 1];
        result[0] = title;
        System.arraycopy(arguments, 0, result, 1, arguments.length);

        return result;
    }
}
