package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ClearMatchmakeCommand extends Command {
    public ClearMatchmakeCommand() {
        super("clear_matchmake");
    }

    @Override
    public void execute() {
        this.getMatchList().clear();
        Message.MATCHMAKE_CLEARED.writeln();
    }
}