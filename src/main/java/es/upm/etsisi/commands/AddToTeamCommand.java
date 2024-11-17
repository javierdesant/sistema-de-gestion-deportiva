package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;

public class AddToTeamCommand extends Command {
    private final Controller controller;

    public AddToTeamCommand(Controller controller) {
        super("team-add", 2);
        this.controller = controller;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);
        String teamName = this.getArgument(1);

        this.controller.addToTeam(playerName, teamName);
    }
}
