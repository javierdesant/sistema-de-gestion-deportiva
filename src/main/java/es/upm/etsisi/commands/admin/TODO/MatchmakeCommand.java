package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.Match;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.PlayerList;
import es.upm.etsisi.utils.Message;

public class MatchmakeCommand extends Command { // TODO: remake for 2.0.0
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