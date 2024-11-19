package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.DifferingTypeException;
import es.upm.etsisi.exceptions.ElementNotFoundException;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class RemoveFromTeamCommand extends Command {
    private final Controller controller;

    public RemoveFromTeamCommand(Controller controller) {
        super("team-remove", 2);
        this.controller = controller;
    }

    @Override
    public void execute() throws ElementNotFoundException, DifferingTypeException {
        String teamName = this.getArgument(0);
        String playerName = this.getArgument(1);

        this.controller.removeFromTeam(teamName, playerName);

        Message.PLAYER_REMOVED_FROM_TEAM.writeln(playerName, teamName);

    }
}
