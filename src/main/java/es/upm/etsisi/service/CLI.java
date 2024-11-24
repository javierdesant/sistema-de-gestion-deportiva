package es.upm.etsisi.service;

import es.upm.etsisi.SportsManager;
import es.upm.etsisi.commands.*;
import es.upm.etsisi.models.User;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class CLI {
    private final SportsManager sportsManager; // move status to the CLI ?
    private final Controller controller;
    private final CommandFactory commandFactory;
    private final Scanner scanner;  // TODO: replace with console

    public CLI(SportsManager sportsManager) { // erase manager param ?
        this.sportsManager = sportsManager;
        this.controller = new Controller();
        this.commandFactory = new CommandFactory(this.controller);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        ErrorType error;

        if (!this.sportsManager.isOpen()) {
            this.sportsManager.open();
        }

        do {
            Command command = this.readCommand();

            if (command != null) {
                error = command.execute();
            } else {
                error = ErrorType.INVALID_COMMAND;
            }

            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (this.sportsManager.isOpen());
    }

    private Command readCommand() {
        User user = this.controller.getUser();

        System.out.println();
        System.out.print(user != null ? user + " # " : "");
        Message.COMMAND_PROMPT.write();

        String input = this.scanner.nextLine().trim();
        String[] tokens = input.split(" ", 2);

        return this.commandFactory.getCommand(user != null ? user.getRole() : null, tokens[0]);
    }
}
