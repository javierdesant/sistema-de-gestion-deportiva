package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;

public class DeleteTeamCommand extends Command {
    private EntityList entityList;

    public DeleteTeamCommand(EntityList entityList) {
        super("team-delete", 0);        // TODO: define Tournament and Match models
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
