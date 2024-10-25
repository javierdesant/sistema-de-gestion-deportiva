package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ShowMatchmakeCommand extends Command {
    private final MatchList matchList;

    public ShowMatchmakeCommand(PlayerList playerList, MatchList matchList) {
        super("show_matchmake");
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        Message.MATCHMAKE_HEADER.writeln();
        this.matchList.show();
        Message.FOOTER.writeln();
    }
}