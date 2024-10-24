package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class RemoveCommand extends Command {
    public RemoveCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "remove");
    }

    @Override
    public void execute() { 
        String playerName = this.getArgument(0);

        if (this.getMatchList().contains(playerName)) {
            WarningMenu menu = new WarningMenu(this.getPlayerList(), this.getMatchList(), this.getName(), playerName);
            menu.execute();
        } else {
            this.getPlayerList().remove(new Player(playerName));
            Message.PLAYER_REMOVED.writeln();
        }
    }
}
