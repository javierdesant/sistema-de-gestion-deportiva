package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ExitCommand extends Command {
    public ExitCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "exit");
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();
    }
}