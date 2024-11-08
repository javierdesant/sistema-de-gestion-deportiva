package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

public class RankCommand extends Command {
    private final ParticipantList participantList;

    public RankCommand(ParticipantList participantList) {
        super("rank");
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        Message.RANKING_HEADER.writeln();
        this.participantList.rank();
        Message.FOOTER.writeln();
    }
}