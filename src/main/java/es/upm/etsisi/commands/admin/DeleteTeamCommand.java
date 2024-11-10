package es.upm.etsisi.commands.admin;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;

public class DeleteTeamCommand extends Command {
    private ParticipantList participantList;

    public DeleteTeamCommand(ParticipantList participantList) {
        super("team-delete", 0);        // TODO: define Tournament and Match models
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        // TODO
    }
}
