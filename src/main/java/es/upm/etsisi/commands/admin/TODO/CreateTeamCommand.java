package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;

public class CreateTeamCommand extends Command {
    private EntityList entityList;

    public CreateTeamCommand(EntityList entityList) {
        super("team-create");
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
