package es.upm.etsisi.models;

import java.util.Iterator;

public class ParticipantList extends List<Participant> {

    public ParticipantList() {
        super();
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
