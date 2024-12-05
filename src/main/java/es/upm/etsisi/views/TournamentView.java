package es.upm.etsisi.views;

import es.upm.etsisi.utils.Console;

import es.upm.etsisi.models.Tournament;

public class TournamentView extends View<Tournament>{

    private TournamentView(){
        super();
    }

    public void write(Tournament tournament){
        Console.getInstance().writeln(tournament.getName());
        Console.getInstance().writeln(tournament.getStartDate().toString() + "-" + tournament.getEndDate().toString());
        Console.getInstance().writeln("Liga: " + tournament.getLeague().toString() + " de " + tournament.getSport().toString());
    }
}
