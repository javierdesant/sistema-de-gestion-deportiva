package es.upm.etsisi.commands.player.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

public class ShowCommand extends Command {  // TODO: remake for 2.0.0º
    private final ParticipantList participantList;

    public ShowCommand(ParticipantList participantList) {
        super("show");
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        this.participantList.show();
        Message.FOOTER.writeln();
    }
}