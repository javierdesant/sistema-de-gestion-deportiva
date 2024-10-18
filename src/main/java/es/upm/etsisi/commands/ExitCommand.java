package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class ExitCommand extends Command {
    public ExitCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "exit", arguments);
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();
    }
}