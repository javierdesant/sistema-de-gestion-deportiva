package es.upm.etsisi.models.game;

import es.upm.etsisi.service.List;
import es.upm.etsisi.views.TournamentListView;

public class TournamentList extends List<Tournament> {
    private final TournamentListView view;

    public TournamentList() {
        super();
        this.view = new TournamentListView(this);
    }

    @Override
    public void add(Tournament element) {

    }

    @Override
    public void remove(Tournament element) {

    }

    @Override
    public void show() {
        this.view.display();
    }
}
