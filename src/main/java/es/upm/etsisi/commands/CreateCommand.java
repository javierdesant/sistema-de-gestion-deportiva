package es.upm.etsisi.commands;

import es.upm.etsisi.Message;
import es.upm.etsisi.Player;
import es.upm.etsisi.PlayerList;

public class CreateCommand extends Command {
    private final PlayerList playerList;

    public CreateCommand(PlayerList playerList) {
        super("create");
        this.playerList = playerList;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.playerList.add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }
}
