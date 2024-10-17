package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class CreateCommand extends Command {
    CreateCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "create", arguments);
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.getPlayerList().add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }
}
