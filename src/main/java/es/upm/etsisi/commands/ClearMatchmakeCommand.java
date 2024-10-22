package es.upm.etsisi.commands;

import es.upm.etsisi.*;

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