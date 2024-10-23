package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.Message;
import es.upm.etsisi.Player;

public class CreateCommand extends Command {
    public CreateCommand() {
        super("create");
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.getPlayerList().add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }
}
