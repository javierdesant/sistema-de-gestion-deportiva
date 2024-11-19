package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.DifferingTypeException;
import es.upm.etsisi.exceptions.ListException;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class AddToTeamCommand extends Command {
    private final Controller controller;

    public AddToTeamCommand(Controller controller) {
        super("team-add", 2);
        this.controller = controller;
    }

    @Override
    public void execute() throws ListException, DifferingTypeException {
        String playerName = this.getArgument(0);
        String teamName = this.getArgument(1);

        this.controller.addToTeam(playerName, teamName);

        Message.PLAYER_ADDED_TO_TEAM.writeln(playerName, teamName);
    }
}
