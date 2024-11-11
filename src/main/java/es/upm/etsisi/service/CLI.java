package es.upm.etsisi.service;

import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.auth.PlayerProfile;
import es.upm.etsisi.models.auth.User;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.commands.admin.AddToTeamCommand;
import es.upm.etsisi.commands.admin.CreateTeamCommand;
import es.upm.etsisi.commands.admin.TODO.*;
import es.upm.etsisi.commands.user.ExitCommand;
import es.upm.etsisi.commands.user.TODO.HelpCommand;
import es.upm.etsisi.commands.user.TODO.LoginCommand;
import es.upm.etsisi.commands.user.TODO.LogoutCommand;
import es.upm.etsisi.models.game.TournamentList;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    private final SportsService service;
    private final LinkedList<Command> commands;
    private final Controller controller;
    private final ParticipantList participantList;
    private final Scanner scanner;
    private final TournamentList tournamentList;

    CLI(ParticipantList participantList, TournamentList tournamentList, SportsService service) {
        assert participantList != null : Message.NULL_PLAYERLIST;   // FIXME: wrong message
        assert tournamentList != null : Message.NULL_MATCHLIST;

        this.commands = new LinkedList<>();
        this.controller = new Controller();
        this.participantList = participantList;
        this.tournamentList = tournamentList;
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public ArrayList<Command> getCommands() {
        return new ArrayList<>(this.commands);
    }

    public void updateCommands() {
        User user = this.controller.getUser();

        this.commands.clear();

        this.addPublicCommands();
        if (user instanceof Administrator) {
            this.addAdminCommands();
        } else if (user instanceof PlayerProfile) {
            this.addPlayerCommands();
        }
    }

    private void addAdminCommands() {
        this.add(new CreatePlayerCommand(this.participantList));
        this.add(new CreateTeamCommand(this.participantList, this.controller));
//        this.add(new DeletePlayerCommand(this.participantList, this.tournamentList, new Scanner(System.in)));     // FIXME
        this.add(new DeleteTeamCommand(this.participantList));
        this.add(new AddToTeamCommand(this.participantList));
        this.add(new RemoveFromTeamCommand(this.participantList));
        // this.add(new CreateTournamentCommand());    // TODO
        // this.add(new DeleteTournamentCommand());     // TODO
        // this.add(new TournamentMatchmakingCommand() or MatchmakeCommand());    // TODO
    }

    private void addPlayerCommands() {
//        this.add(new EnrollCommand());
//        this.add(new LeaveCommand());
//        this.add(new ShowCommand());
        // add tournament related commands...       // TODO
        // this.add(new ShowStatsCommand());        // TODO
    }

    private void addPublicCommands() {
        this.add(new LoginCommand(this.controller, this));
        this.add(new LogoutCommand(this.controller, this));
        this.add(new HelpCommand(this));
        this.add(new ExitCommand(this.service));
    }

    private void add(Command command) {
        this.commands.add(command);
    }

    protected void run() {
        if (!this.service.isOpen()) {
            this.service.open();
        }

        do {
            Command command = this.readCommand();

            if (command != null) {
                try {
                    command.execute();
                } catch (AssertionError error) {    // FIXME
                    System.out.println("Error: " + error.getMessage());
                }
            }

        } while (this.service.isOpen());
    }

    public Command readCommand() {
        User user = controller.getUser();

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
