package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class HelpCommand extends Command {
    public HelpCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "help");
    }

    @Override
    public void execute() {
        Message.HELP_MESSAGE.writeln();
    }
}