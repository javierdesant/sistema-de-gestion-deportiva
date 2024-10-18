package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class HelpCommand extends Command {
    public HelpCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "help", arguments);
    }

    @Override
    public void execute() {
        Message.HELP_MESSAGE.writeln();
    }
}