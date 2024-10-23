package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.Message;

public class ShowMatchmakeCommand extends Command {
    public ShowMatchmakeCommand() {
        super("show_matchmake");
    }

    @Override
    public void execute() {
        Message.MATCHMAKE_HEADER.writeln();
        getMatchList().show();
        Message.FOOTER.writeln();
    }
}