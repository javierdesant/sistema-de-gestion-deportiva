package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class ShowCommand extends Command {
    public ShowCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "show", arguments);
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        getPlayerList().show();
        Message.FOOTER.writeln();
    }
}