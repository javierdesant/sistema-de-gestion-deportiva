package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.Role;
import es.upm.etsisi.service.AuthenticationService;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.service.TournamentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class CommandFactory {
    private final AuthenticationService authenticationService;
    private final ParticipantService participantService;
    private final TournamentService tournamentService;
    private final HashMap<Role, ArrayList<Command>> commands;
    private final ArrayList<Command> publicCommands;

    public CommandFactory(AuthenticationService authenticationService, ParticipantService participantService,
            TournamentService tournamentService) {
        this.authenticationService = authenticationService;
        this.participantService = participantService;
        this.tournamentService = tournamentService;
        this.commands = new HashMap<>();
        this.publicCommands = new ArrayList<>();

        this.initializePublicCommands();
        this.initializeCommands();
    }

    private void initializeCommands() {
        this.commands.put(Role.ADMIN, createCommands(
                new CreatePlayerCommand(this.participantService),
                new CreateTeamCommand(this.participantService),
                new DeletePlayerCommand(this.participantService),
                new DeleteTeamCommand(this.participantService),
                new AddToTeamCommand(this.participantService),
                new RemoveFromTeamCommand(this.participantService),
                new CreateTournamentCommand(this.tournamentService),
                new DeleteTournamentCommand(this.tournamentService),
                new MatchmakeCommand(this.tournamentService)));

        this.commands.put(Role.PLAYER, createCommands(
                new EnrollCommand(this.tournamentService),
                new LeaveCommand(this.tournamentService),
                new ShowStatsCommand(this.participantService)));
    }

    private void initializePublicCommands() {
        this.publicCommands.add(new LoginCommand(this.authenticationService));
        this.publicCommands.add(new LogoutCommand(this.authenticationService));
        this.publicCommands.add(new ListTournamentsCommand(tournamentService));
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
