package es.upm.etsisi.views;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;
import es.upm.etsisi.DefaultParameters;
import es.upm.etsisi.models.DNI;
import es.upm.etsisi.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

        String testFile = "sistema-de-gestion-deportiva/src/test/java/es/upm/etsisi/resources/default_output.txt";
        try {
            this.addDefaults(testFile);
        } catch (IOException exception) {
            exception.toString();
            exception.printStackTrace();
        }

        do {
            User user = this.controller.getUser();
            String input = this.readInput();
            String[] parsedInput = this.splitInput(input);
            commandTitle = parsedInput[0];

            Command command = this.commandFactory.getCommand(user != null ? user.getRole() : null, commandTitle);
            if (command != null) {
                error = command.execute(Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
                if (command.isCalled("help")) {
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

    private void displayHelp() {
        Console console = Console.getInstance();
        User user = this.controller.getUser();

        console.writeln("Comandos disponibles:");
        ArrayList<Command> commands = this.commandFactory.getAllCommands(user != null ? user.getRole() : null);
        for (Command command : commands) {
            console.writeln(" - " + command.getName() + ": " + command.getDescription());
        }
    }

    private void addDefaults(String fileName) throws IOException {
        LinkedList<LinkedList<String>> defaultParameters = DefaultParameters.getDefaultParticipants();
        Iterator<LinkedList<String>> iterator = defaultParameters.iterator();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("Parámetros generados aleatoriamente:\n\n");

        while (iterator.hasNext()) {
            LinkedList<String> currentParameters = iterator.next();

            if (User.isUpmEmail(currentParameters.get(0))) {
                this.controller.createPlayer(currentParameters.get(0), currentParameters.get(1), currentParameters.get(2),
                        currentParameters.get(3), DNI.generateDNI());

                for (String parameter : currentParameters) {
                    writer.write(parameter + "\t");
                }
                writer.newLine();
            } else {
                this.controller.createTeam(currentParameters.get(0), currentParameters.get(1));
            }
        }
        writer.close();
    }
}
