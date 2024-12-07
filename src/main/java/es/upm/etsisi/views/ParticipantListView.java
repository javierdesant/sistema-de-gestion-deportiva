package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Participant;

public class ParticipantListView extends ListView<Participant> {
    public ParticipantListView() {
        super();
    }

    @Override
    public void write(List<Participant> participant) {
        this.writeList(participant);
    }

    @Override
    public void display(Participant participant) {
        new ParticipantView().display(participant);
    }
}
