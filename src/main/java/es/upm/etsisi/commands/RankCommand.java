package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class RankCommand extends Command {
    public RankCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "rank");
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        getPlayerList().rank();
        Message.FOOTER.writeln();
    }
}