package es.upm.etsisi.commands.admin;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.entities.Team;
import es.upm.etsisi.service.Controller;

public class CreateTeamCommand extends Command {
    private final ParticipantList participantList;
    private final Controller controller;

    public CreateTeamCommand(ParticipantList participantList, Controller controller) {
        super("team-create", 1);
        this.participantList = participantList;
        this.controller = controller;
    }

    @Override
    public void execute() {
        assert this.controller.getUser() instanceof Administrator;

        String teamName = this.getArgument(0);
        Administrator admin = (Administrator) this.controller.getUser();

        // should we use !(this.entityList.getByName(teamName) instanceof Team) ?
        if (this.participantList.getByName(teamName) == null) {
            this.participantList.add(new Team(teamName, admin.getUsername()));
        } else {
            System.out.println("error: the team already exists");
        }
    }
}
