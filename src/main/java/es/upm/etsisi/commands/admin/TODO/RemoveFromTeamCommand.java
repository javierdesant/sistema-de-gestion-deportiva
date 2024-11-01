package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;

public class RemoveFromTeamCommand extends Command {
    private EntityList entityList;

    public RemoveFromTeamCommand(EntityList entityList) {
        super("team-remove");
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
