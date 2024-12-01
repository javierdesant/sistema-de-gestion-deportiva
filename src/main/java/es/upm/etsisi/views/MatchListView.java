package es.upm.etsisi.views;

import es.upm.etsisi.models.Match;
import es.upm.etsisi.utils.Console;
import es.upm.etsisi.models.List;

public class MatchListView extends ListView<Match> {
    private static final MatchListView instance = new MatchListView();

    private MatchListView(){

    }
    
    public static MatchListView getInstance(){
        return instance;
    }

    public void write(List<Match> list){
        Console.getInstance().writeln("     PARTIDOS     ");
        Console.getInstance().writeln("------------------");
        super.writeList(list);
    }
    @Override
    protected void writeElement(Match match){
        MatchView.getInstance().write(match);
    } 
}
