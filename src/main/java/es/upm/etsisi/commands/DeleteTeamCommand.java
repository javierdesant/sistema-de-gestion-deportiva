package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.ElementNotFoundException;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class DeleteTeamCommand extends Command {
    private final Controller controller;

    public DeleteTeamCommand(Controller controller) {
        super("team-delete", 1);
        this.controller = controller;
    }

    @Override
    public void execute() throws ElementNotFoundException {
        String teamName = this.getArgument(0);

        this.controller.deleteParticipant(teamName);

        Message.TEAM_REMOVED.writeln();
    }
}
