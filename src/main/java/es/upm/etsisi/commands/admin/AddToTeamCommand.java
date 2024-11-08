package es.upm.etsisi.commands.admin;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.Team;

public class AddToTeamCommand extends Command {
    private final ParticipantList participantList;

    public AddToTeamCommand(ParticipantList participantList) {
        super("team-add", 2);
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);
        String teamName = this.getArgument(1);

        Participant participant1 = this.participantList.getByName(teamName);
        Participant participant2 = this.participantList.getByName(playerName);

        if (participant1 instanceof Team team && participant2 instanceof Player player) {
            team.add(player);
        } else {
            System.out.println("error: No se puede agregar el objeto");    // TODO: fix error display
        }
    }
}
