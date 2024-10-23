package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ShowCommand extends Command {
    public ShowCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "show");
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        getPlayerList().show();
        Message.FOOTER.writeln();
    }
}