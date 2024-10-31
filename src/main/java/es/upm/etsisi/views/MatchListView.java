package es.upm.etsisi.views;

import es.upm.etsisi.models.game.MatchList;

public class MatchListView implements View {
    private final MatchList matchList;

    public MatchListView(MatchList matchList) {
        this.matchList = matchList;
    }

    @Override
    public void display() {
        System.out.println(this.matchList); // TODO
    }
}
