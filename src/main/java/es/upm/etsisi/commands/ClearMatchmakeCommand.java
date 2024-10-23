package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ClearMatchmakeCommand extends Command {
    public ClearMatchmakeCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "clear_matchmake");
    }

    @Override
    public void execute() {
        getMatchList().clear();
        Message.MATCHMAKE_CLEARED.writeln();
    }
}