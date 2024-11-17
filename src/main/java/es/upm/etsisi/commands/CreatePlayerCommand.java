package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.utils.Message;

public class CreatePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final Controller controller;

    public CreatePlayerCommand(Controller controller) {
        super("player-create", 0);
        this.controller = controller;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

//        this.entityList.add(new Player(playerName));      // FIXME
        Message.PLAYER_ADDED.writeln();
    }
}
