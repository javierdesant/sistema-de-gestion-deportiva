package es.upm.etsisi.views;

import es.upm.etsisi.models.User;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class CLI {
    private final Controller controller;
    private final CommandFactory commandFactory;

    public CLI() {
        this.controller = new Controller();
        this.commandFactory = new CommandFactory(this.controller);
    }

    public boolean isOpen(String commandTitle) {
        return !commandTitle.equals("exit");
    }

    public void run() {
        ErrorType error;
        String commandTitle;

        do {
            User user = this.controller.getUser();
            String input = this.readInput();
            String[] parsedInput = this.splitInput(input);
            commandTitle = parsedInput[0];

            Command command = this.commandFactory.getCommand(user.getRole(), commandTitle);
            if (command != null) {
                error = command.execute(Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
                if (commandTitle.equals("help")) {      // FIXME: i dont like this
                    this.displayHelp();
                }
            } else {
                error = ErrorType.INVALID_COMMAND;
            }

            new ErrorView(error).writeln();
        } while (this.isOpen(commandTitle));
    }

    private String readInput() {
        Console console = Console.getInstance();
        User user = this.controller.getUser();

        console.writeln();
        if (user.isLoggedIn()) {
            console.write(user + " ");
        }

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

    private void displayHelp() {
        Console console = Console.getInstance();
        User user = this.controller.getUser();

        console.writeln("Comandos disponibles:");
        ArrayList<Command> commands = this.commandFactory.getAllCommands(user.getRole());
        for (Command command : commands) {
            console.writeln(" - " + command.getName() + ": " + command.getDescription());
        }
    }
}
