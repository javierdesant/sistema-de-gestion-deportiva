package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class MatchmakeCommand extends Command {
    private final PlayerList playerList;
    private final MatchList matchList;

    public MatchmakeCommand(PlayerList playerList, MatchList matchList) {
        super("matchmake");
        this.playerList = playerList;
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        String homePlayerName = this.getArgument(0);
        String visitingPlayerName = this.getArgument(1);

        this.matchList.add(new Match(this.playerList, new Player(homePlayerName), new Player(visitingPlayerName)));
        Message.PLAYERS_MATCHED.writeln(homePlayerName, visitingPlayerName);
    }
}