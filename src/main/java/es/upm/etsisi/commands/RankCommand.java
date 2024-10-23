package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.Message;

public class RankCommand extends Command {
    public RankCommand() {
        super("rank");
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        getPlayerList().rank();
        Message.FOOTER.writeln();
    }
}