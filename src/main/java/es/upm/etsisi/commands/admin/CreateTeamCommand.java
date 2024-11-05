package es.upm.etsisi.commands.admin;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.models.entities.Team;
import es.upm.etsisi.service.AuthController;

public class CreateTeamCommand extends Command {
    private final EntityList entityList;
    private final AuthController authController;

    public CreateTeamCommand(EntityList entityList, AuthController authController) {
        super("team-create", 1);
        this.entityList = entityList;
        this.authController = authController;
    }

    @Override
    public void execute() {
        assert this.authController.getUser() instanceof Administrator;

        String teamName = this.getArgument(0);
        Administrator admin = (Administrator) this.authController.getUser();

            // should we use !(this.entityList.getByName(teamName) instanceof Team) ?
        if (this.entityList.getByName(teamName) == null) {
            this.entityList.add(new Team(teamName, admin.getUsername()));
        } else {
            System.out.println("error: the team already exists");
        }
    }
}
