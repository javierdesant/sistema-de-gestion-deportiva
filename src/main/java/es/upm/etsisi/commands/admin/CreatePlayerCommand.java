package es.upm.etsisi.commands.admin;

import es.upm.etsisi.Message;
import es.upm.etsisi.Player;
import es.upm.etsisi.PlayerList;
import es.upm.etsisi.commands.Command;

public class CreatePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final PlayerList playerList;

    public CreatePlayerCommand(PlayerList playerList) {
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
