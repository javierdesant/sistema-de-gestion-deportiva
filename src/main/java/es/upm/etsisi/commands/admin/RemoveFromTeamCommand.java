package es.upm.etsisi.commands.admin;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;

public class RemoveFromTeamCommand extends Command {
    private ParticipantList participantList;

    public RemoveFromTeamCommand(ParticipantList participantList) {
        super("team-remove", 2);
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
