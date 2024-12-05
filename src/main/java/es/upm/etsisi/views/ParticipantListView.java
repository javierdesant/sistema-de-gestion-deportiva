package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Participant;

public class ParticipantListView extends ListView<Participant> {
    private ParticipantView participantView;

    public ParticipantListView() {
        super();
    }

    @Override
    public void write(List<Participant> participant) {
        this.writeList(participant);
    }

    @Override
    protected void write(Participant participant) {
        participantView.write(participant);
    }


}
