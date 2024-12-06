package es.upm.etsisi.views;

import es.upm.etsisi.models.User;
import es.upm.etsisi.service.AuthenticationService;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.views.commands.Command;
import es.upm.etsisi.views.commands.CommandFactory;
import es.upm.etsisi.views.commands.ParsedInput;

import java.util.ArrayList;

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

            command = this.commandFactory.getCommand(user.getRole(), ParsedInput.getTitle(input));
            if (command != null) {
                error = command.execute(input);
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

        return console.readString(CommandFeedback.COMMAND_PROMPT.toString());
    }

    private void displayHelp() {
        Console console = Console.getInstance();
        User user = this.authenticationService.getUser();

        console.writeln("Comandos disponibles:");
        ArrayList<Command> commands = this.commandFactory.getAllCommands(user.getRole());
        for (Command command : commands) {
            console.writeln(" - " + command.getTitle() + ": " + command.getDescription());
        }
    }
}
