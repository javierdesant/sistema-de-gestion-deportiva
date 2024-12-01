package es.upm.etsisi.views;

import es.upm.etsisi.models.Participant;
import es.upm.etsisi.models.List;

public class ParticipantListView extends ListView<Participant> {
    
    private static final ParticipantListView instance = new ParticipantListView();

    private ParticipantListView(){
    }

    public ParticipantListView getInstace(){
        return instance;
    }

    public void write(List<Participant> participant){
        super.writeList(participant);
    }
    @Override
    protected void writeElement(Participant participant){
        ParticipantView.getInstance().write(participant);
    }
    


    
}
