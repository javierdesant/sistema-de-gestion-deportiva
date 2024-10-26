package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

public class RankCommand extends Command {
    private final EntityList entityList;

    public RankCommand(EntityList entityList) {
        super("rank");
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        this.entityList.rank();
        Message.FOOTER.writeln();
    }
}