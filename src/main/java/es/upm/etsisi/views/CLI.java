package es.upm.etsisi.views;

import es.upm.etsisi.models.User;
import es.upm.etsisi.service.AuthenticationService;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class CLI {
    private final AuthenticationService authenticationService;
    private final ParticipantService participantService;
    private final TournamentService tournamentService;
    private final CommandFactory commandFactory;

    public CLI() {
        this.authenticationService = new AuthenticationService();
        this.participantService = new ParticipantService(authenticationService);
        this.tournamentService = new TournamentService(authenticationService);
        this.commandFactory = new CommandFactory(authenticationService, participantService, tournamentService);
    }

    public boolean isOpen(Command command) {
        if (command == null) {
            return true;
        }
        return !command.isCalled("exit");
    }

    public void run() {
        ErrorType error;
        Command command;

        do {
            error = ErrorType.NULL;
            User user = this.authenticationService.getUser();
            String input = this.readInput();
            String[] parsedInput = this.splitInput(input);
            String commandTitle = parsedInput[0];

            command = this.commandFactory.getCommand(user.getRole(), commandTitle);
            if (command != null) {
                error = command.execute(Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
                if (command.isCalled("help")) {
                    this.displayHelp();
                }
            } else if (!input.isBlank()) {
                error = ErrorType.INVALID_COMMAND;
            }

            new ErrorView(error).writeln();
        } while (this.isOpen(command));
    }

    private String readInput() {
        Console console = Console.getInstance();
        User user = this.authenticationService.getUser();

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
        User user = this.authenticationService.getUser();

        console.writeln("Comandos disponibles:");
        ArrayList<Command> commands = this.commandFactory.getAllCommands(user.getRole());
        for (Command command : commands) {
            console.writeln(" - " + command.getName() + ": " + command.getDescription());
        }
    }
}
