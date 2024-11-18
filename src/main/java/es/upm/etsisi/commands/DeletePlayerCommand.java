package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.NonExistElement;
import es.upm.etsisi.service.Controller;

public class DeletePlayerCommand extends Command {
    private final Controller controller;

    public DeletePlayerCommand(Controller controller) {
        super("player-remove", 1);
        this.controller = controller;
    }

    @Override
    public void execute() throws NonExistElement {
        String playerName = this.getArgument(0);

        try {
            this.controller.deleteParticipant(playerName);
        } catch (NonExistElement exception) {
            exception.toString();
            exception.printStackTrace();
        }
    }
}
