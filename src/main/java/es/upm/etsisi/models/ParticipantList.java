package es.upm.etsisi.models;

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
