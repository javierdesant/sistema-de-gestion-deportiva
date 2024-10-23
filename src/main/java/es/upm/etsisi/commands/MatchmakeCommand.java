package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.Match;
import es.upm.etsisi.Message;
import es.upm.etsisi.Player;

public class MatchmakeCommand extends Command {
    public MatchmakeCommand() {
        super("matchmake");
    }

    @Override
    public void execute() {
        String homePlayerName = this.getArgument(0);
        String visitingPlayerName = this.getArgument(1);

        this.getMatchList().add(new Match(this.getPlayerList(), new Player(homePlayerName), new Player(visitingPlayerName)));
        Message.PLAYERS_MATCHED.writeln(homePlayerName, visitingPlayerName);
    }
}