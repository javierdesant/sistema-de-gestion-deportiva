package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.MatchList;
import es.upm.etsisi.utils.Message;

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