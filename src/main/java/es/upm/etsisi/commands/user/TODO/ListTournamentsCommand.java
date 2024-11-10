package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.Tournament;
import es.upm.etsisi.models.game.TournamentList;
import es.upm.etsisi.service.DisplayService;

public class ListTournamentsCommand extends Command {
    TournamentList tournaments;

    public ListTournamentsCommand(TournamentList tournaments) {
        super("tournaments", 0);
        this.tournaments = tournaments;
    }

    @Override
    public void execute() {
        for (Tournament tournament : this.tournaments.getElements()) {
            DisplayService.show(tournament);
        }
    }
}
