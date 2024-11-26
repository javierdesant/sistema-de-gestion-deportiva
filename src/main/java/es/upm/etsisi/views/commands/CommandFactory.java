package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.Role;
import es.upm.etsisi.service.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class CommandFactory {
    private final Controller controller;
    private final HashMap<Role, ArrayList<Command>> commands;
    private final ArrayList<Command> publicCommands;

    public CommandFactory(Controller controller) {
        this.controller = controller;
        this.commands = new HashMap<>();
        this.publicCommands = new ArrayList<>();

        this.initializePublicCommands();
        this.initializeCommands();
    }

    private void initializeCommands() {
        this.commands.put(Role.ADMIN, createCommands(
                new CreatePlayerCommand(this.controller),
                new CreateTeamCommand(this.controller),
                new DeletePlayerCommand(this.controller),
                new DeleteTeamCommand(this.controller),
                new AddToTeamCommand(this.controller),
                new RemoveFromTeamCommand(this.controller),
                new CreateTournamentCommand(this.controller),
                new DeleteTournamentCommand(this.controller),
                new MatchmakeCommand(this.controller)
        ));

        this.commands.put(Role.PLAYER, createCommands(
                new EnrollCommand(this.controller),
                new LeaveCommand(this.controller),
                new ShowStatsCommand(this.controller)
        ));
    }

    private void initializePublicCommands() {
        this.publicCommands.add(new LoginCommand(this.controller));
        this.publicCommands.add(new LogoutCommand(this.controller));
        this.publicCommands.add(new HelpCommand());
        this.publicCommands.add(new ExitCommand());
    }

    private ArrayList<Command> createCommands(Command... specificCommands) {
        ArrayList<Command> commandList = new ArrayList<>(this.publicCommands);
        commandList.addAll(Arrays.asList(specificCommands));
        return commandList;
    }

    public ArrayList<Command> getAllCommands(Role role) {
        return this.commands.getOrDefault(role, this.publicCommands);
    }

    public Command getCommand(Role role, String title) {
        ArrayList<Command> activeCommands = this.commands.getOrDefault(role, this.publicCommands);
        Command res = null;

        Iterator<Command> iterator = activeCommands.iterator();
        while (iterator.hasNext() && res == null) {
            Command command = iterator.next();
            if (command.isCalled(title)) {
                res = command;
            }
        }

        return res;
    }
}
