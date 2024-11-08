package es.upm.etsisi.models.entities;

import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class ParticipantList extends List<Participant> {

    public ParticipantList() {
        super();
    }

    @Override
    public void add(Participant participant) {
        assert !this.contains(participant) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.addElement(participant);
    }

    @Override
    public void remove(Participant participant) {
        boolean removed = this.removeElement(participant);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    public void rank() {
        this.show();    // TODO
    }

    public Participant getByName(String name) {
        Participant res = null;

        Iterator<Participant> iterator = this.getElements().iterator();
        while (iterator.hasNext() && res == null) {
            Participant participant = iterator.next();
            if (participant.getName().equals(name)) {
                res = participant;
            }
        }

        return res;
    }
}
