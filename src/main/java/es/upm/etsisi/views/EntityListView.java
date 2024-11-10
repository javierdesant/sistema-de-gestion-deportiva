package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

public class EntityListView implements ModelView {
    private final ParticipantList participantList;

    public EntityListView(ParticipantList participantList) {
        this.participantList = participantList;
    }

    @Override
    public void display() {
        if (this.participantList.isEmpty()) {
            Message.NO_PLAYERS.writeln();   // TODO: replace with NO_MEMBERS Message
        } else {
            // TODO: add header Message
            for (Participant participant : this.participantList.getElements()) {
                participant.show();
                Message.LIGHT_FOOTER.writeln();
            }
            Message.FOOTER.writeln();
        }
    }
}
