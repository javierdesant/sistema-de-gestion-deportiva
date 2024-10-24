package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;

public class ClearMatchmakeCommand extends Command {
    private final MatchList matchList;

    public ClearMatchmakeCommand(MatchList matchList) {
        super("clear_matchmake");
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        this.matchList.clear();
        Message.MATCHMAKE_CLEARED.writeln();
    }
}