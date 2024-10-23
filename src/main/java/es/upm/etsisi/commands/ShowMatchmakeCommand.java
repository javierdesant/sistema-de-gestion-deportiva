package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ShowMatchmakeCommand extends Command {
    public ShowMatchmakeCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "show_matchmake");
    }

    @Override
    public void execute() {
        Message.MATCHMAKE_HEADER.writeln();
        getMatchList().show();
        Message.FOOTER.writeln();
    }
}