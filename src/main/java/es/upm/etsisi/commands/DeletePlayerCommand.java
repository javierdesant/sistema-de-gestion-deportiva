package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.NonExistElement;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class DeletePlayerCommand extends Command {
    private final Controller controller;

    public DeletePlayerCommand(Controller controller) {
        super("player-remove", 1);
        this.controller = controller;
    }

    @Override
    public void execute() throws NonExistElement {
        String playerName = this.getArgument(0);

        this.controller.deleteParticipant(playerName);

        Message.PLAYER_REMOVED.writeln();
    }
}
