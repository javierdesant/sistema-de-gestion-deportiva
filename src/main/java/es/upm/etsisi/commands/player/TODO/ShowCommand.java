package es.upm.etsisi.commands.player.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

public class ShowCommand extends Command {  // TODO: remake for 2.0.0º
    private final EntityList entityList;

    public ShowCommand(EntityList entityList) {
        super("show");
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        this.entityList.show();
        Message.FOOTER.writeln();
    }
}