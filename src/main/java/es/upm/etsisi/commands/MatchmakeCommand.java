package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class MatchmakeCommand extends Command {
    public MatchmakeCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "matchmake", arguments);
    }

    @Override
    public void execute() {
        String homePlayerName = this.getArgument(0);
        String visitingPlayerName = this.getArgument(1);

        this.getMatchList().add(new Match(this.getPlayerList(), new Player(homePlayerName), new Player(visitingPlayerName)));
        Message.PLAYERS_MATCHED.writeln(homePlayerName, visitingPlayerName);
    }
}