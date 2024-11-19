package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.DuplicateElementException;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.DNI;
import es.upm.etsisi.utils.Message;

public class CreatePlayerCommand extends Command {
    private final Controller controller;

    public CreatePlayerCommand(Controller controller) {
        super("player-create", 5);
        this.controller = controller;
    }

    @Override
    public void execute() throws DuplicateElementException {
        String username = this.getArgument(0);
        String password = this.getArgument(1);
        String playerName = this.getArgument(2);
        String playerLastName = this.getArgument(3);
        DNI dni = new DNI(this.getArgument(4));

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.controller.createPlayer(username, password, playerLastName, playerLastName, dni);

        Message.PLAYER_ADDED.writeln();
    }
}
