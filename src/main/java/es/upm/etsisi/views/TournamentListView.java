package es.upm.etsisi.views;

import es.upm.etsisi.models.List;
import es.upm.etsisi.models.Tournament;

public class TournamentListView extends ListView<Tournament> {

    public TournamentListView() {
        super();
    }

    @Override
    public void write(List<Tournament> list) {
        this.writeList(list);
    }

    @Override
    public void display(Tournament tournament) {
        new TournamentView().display(tournament);
    }
}
