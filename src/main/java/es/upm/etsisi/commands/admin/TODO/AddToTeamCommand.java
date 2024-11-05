package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.Entity;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.Team;

public class AddToTeamCommand extends Command {
    private final EntityList entityList;

    public AddToTeamCommand(EntityList entityList) {
        super("team-add", 2);
        this.entityList = entityList;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);
        String teamName = this.getArgument(1);

        Entity entity1 = this.entityList.getByName(teamName);
        Entity entity2 = this.entityList.getByName(playerName);

        if (entity1 instanceof Team team && entity2 instanceof Player player) {
            team.add(player);
        } else {
            System.out.println("error: No se puede agregar el objeto");    // TODO: fix error display
        }
    }
}
