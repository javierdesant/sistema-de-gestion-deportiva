package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class CreateCommand extends Command {
    CreateCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "create");
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.getPlayerList().add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }
}
