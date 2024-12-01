package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Tournament;

public class TournamentListView extends ListView<Tournament> {

    private static final TournamentListView instance = new TournamentListView();

    private TournamentListView(){
    }

    public static TournamentListView getInstance(){
        return instance;
    }
    @Override
    public void write(List<Tournament> list) {
        super.writeList(list);
    }
    @Override
    protected void writeElement(Tournament elemenT) {
        TournamentView.getInstance().write(elemenT);
    }
}
