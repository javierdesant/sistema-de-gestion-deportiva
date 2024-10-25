package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.PlayerList;
import es.upm.etsisi.utils.Message;

public class RankCommand extends Command {
    private final PlayerList playerList;

    public RankCommand(PlayerList playerList) {
        super("rank");
        this.playerList = playerList;
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        this.playerList.rank();
        Message.FOOTER.writeln();
    }
}