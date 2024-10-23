package es.upm.etsisi.commands;

import es.upm.etsisi.Command;
import es.upm.etsisi.Message;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show");
    }

    @Override
    public void execute() {
        Message.PLAYERLIST_HEADER.writeln();
        getPlayerList().show();
        Message.FOOTER.writeln();
    }
}