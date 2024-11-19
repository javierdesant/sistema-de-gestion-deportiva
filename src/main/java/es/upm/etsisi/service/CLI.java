package es.upm.etsisi.service;

import es.upm.etsisi.SportsManager;
import es.upm.etsisi.commands.*;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.User;
import es.upm.etsisi.utils.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    private final SportsManager sportsManager; // move status to the CLI ?
    private final LinkedList<Command> commands;
    private final Controller controller;
    private final Scanner scanner;

    public CLI(SportsManager sportsManager) { // erase manager param ?
        this.commands = new LinkedList<>();
        this.controller = new Controller();
        this.sportsManager = sportsManager;
        this.scanner = new Scanner(System.in);
    }

    public ArrayList<Command> getCommands() {
        return new ArrayList<>(this.commands);
    }

    public void updateCommands() {
        User user = this.controller.getUser();

        this.commands.clear();

        this.addPublicCommands();
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                this.addAdminCommands();
            } else if (user.getRole() == Role.PLAYER) {
                this.addPlayerCommands();
            }
        }
    }

    private void addAdminCommands() {
        // this.add(new CreatePlayerCommand(this.controller));
        // this.add(new CreateTeamCommand(this.controller));
        // this.add(new DeletePlayerCommand(this.controller, this.tournamentList, new
        // Scanner(System.in))); // FIXME
        // this.add(new DeleteTeamCommand(this.controller));
        this.add(new AddToTeamCommand(this.controller));
        // this.add(new RemoveFromTeamCommand(this.controller));
        // this.add(new CreateTournamentCommand()); // TODO
        // this.add(new DeleteTournamentCommand()); // TODO
        // this.add(new TournamentMatchmakingCommand() or MatchmakeCommand()); // TODO
    }

    private void addPlayerCommands() {
        // this.add(new EnrollCommand());
        // this.add(new LeaveCommand());
        // this.add(new ShowCommand());
        // add tournament related commands... // TODO
        // this.add(new ShowStatsCommand()); // TODO
    }

    private void addPublicCommands() {
        this.add(new LoginCommand(this.controller, this));
        this.add(new LogoutCommand(this.controller, this));
        this.add(new HelpCommand(this));
        this.add(new ExitCommand(this.sportsManager));
    }

    private void add(Command command) {
        this.commands.add(command);
    }

    public void run() {
        if (!this.sportsManager.isOpen()) {
            this.sportsManager.open();
        }
        do {
            Command command = this.readCommand();

            if (command != null) {
                try {
                    command.execute();
                } catch (AssertionError error) { // FIXME
                    System.out.println("Error: " + error.getMessage());
                } catch (Exception exception) {
                    exception.toString();
                    exception.printStackTrace();
                }
            }
        } while (this.sportsManager.isOpen());
    }

    private Command readCommand() {
        User user = this.controller.getUser();

        System.out.println();
        System.out.print(user == null ? "" : user + " # ");
        Message.COMMAND_PROMPT.write();

        String input = this.scanner.nextLine().trim();

        Command res = null;
        Iterator<Command> iterator = this.commands.iterator();
        while (iterator.hasNext() && res == null) {
            Command command = iterator.next();
            if (command.isCalled(input)) {
                command.validate(input);
                res = command;
            }
        }

        if (res == null) {
            Message.INVALID_COMMAND.writeln();
        }

        return res;
    }
}
