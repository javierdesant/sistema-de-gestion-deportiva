package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Tournament;

public class TournamentListView extends ListView<Tournament> {

    private TournamentView tournamentView;

    public TournamentListView(){
        super();
    }

    @Override
    public void write(List<Tournament> list) {
        super.writeList(list);
    }

    @Override
    protected void write(Tournament elemenT) {
        tournamentView.write(elemenT);
    }
}
