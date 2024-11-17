package es.upm.etsisi.commands;

import es.upm.etsisi.models.Tournament;
import es.upm.etsisi.models.TournamentList;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.DisplayService;

public class ListTournamentsCommand extends Command {
    private final Controller controller;

    public ListTournamentsCommand(Controller controller) {
        super("tournaments", 0);
        this.controller = controller;
    }

    @Override
    public void execute() {
        TournamentList tournaments = this.controller.getTournaments();

        for (Tournament tournament : tournaments.getElements()) {
            DisplayService.show(tournament);
        }
    }
}
