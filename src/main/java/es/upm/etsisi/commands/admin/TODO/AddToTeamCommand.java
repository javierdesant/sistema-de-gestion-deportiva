package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;

public class AddToTeamCommand extends Command {
    private EntityList entityList;

    public AddToTeamCommand(EntityList entityList) {
        super("team-add");
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
