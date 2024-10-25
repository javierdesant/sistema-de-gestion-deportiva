package es.upm.etsisi.commands.player;

import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;
import es.upm.etsisi.commands.Command;

public class ShowCommand extends Command {  // TODO: remake for 2.0.0º
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