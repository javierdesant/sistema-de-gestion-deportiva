package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class RandomMatchmakeCommand extends Command {
    public RandomMatchmakeCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "random_matchmake");
    }

    @Override
    public void execute() {
        if (getMatchList().isEmpty()) {
            getMatchList().randomize(getPlayerList());
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
            WarningMenu menu = new WarningMenu(this.getPlayerList(), this.getMatchList(), this.getName(), null);
            menu.execute();
        }
    }
}