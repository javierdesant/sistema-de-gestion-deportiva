package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

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