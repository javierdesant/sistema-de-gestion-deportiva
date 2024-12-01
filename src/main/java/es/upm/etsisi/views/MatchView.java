package es.upm.etsisi.views;

import es.upm.etsisi.models.Match;
import es.upm.etsisi.utils.Console;

public class MatchView implements View<Match> {

    private static final MatchView instance = new MatchView();
    
    private MatchView(){
    }

    public static MatchView getInstance(){
        return instance;
    }

    public void write(Match element) {
        Console.getInstance().writeln(element.toString());
    }
}
