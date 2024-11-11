package es.upm.etsisi.models.game;

import es.upm.etsisi.service.List;

public class TournamentList extends List<Tournament> {
    public TournamentList() {
        super();
    }

    @Override
    public void add(Tournament tournament) {
        assert !this.contains(tournament);    // TODO: use exceptions

        this.addElement(tournament);
    }

    @Override
    public void remove(Tournament tournament) {
        assert this.contains(tournament);

        this.removeElement(tournament);
    }
}
