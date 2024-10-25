package es.upm.etsisi.commands;

import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ShowCommand extends Command {
    private final PlayerList playerList;

    public ShowCommand(PlayerList playerList) {
        super("show");
        this.playerList = playerList;
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        this.playerList.show();
        Message.FOOTER.writeln();
    }
}