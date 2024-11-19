package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.DuplicateElementException;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class CreateTeamCommand extends Command {
    private final Controller controller;

    public CreateTeamCommand(Controller controller) {
        super("team-create", 1);
        this.controller = controller;
    }

    @Override
    public void execute() throws DuplicateElementException {
        String teamName = this.getArgument(0);

        assert teamName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.controller.createTeam(teamName);

        Message.TEAM_ADDED.writeln();
    }
}
