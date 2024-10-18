package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class RankCommand extends Command {
    public RankCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "rank", arguments);
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        getPlayerList().rank();
        Message.FOOTER.writeln();
    }
}