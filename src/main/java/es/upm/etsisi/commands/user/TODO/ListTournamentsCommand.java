package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.TournamentList;

public class ListTournamentsCommand extends Command {
    TournamentList tournaments;

    public ListTournamentsCommand(TournamentList tournaments) {
        super("tournaments");
        this.tournaments = tournaments;
    }

    @Override
    public void execute() {
        // TODO
    }
}
