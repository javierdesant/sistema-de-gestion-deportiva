package es.upm.etsisi.commands.user;

import es.upm.etsisi.Message;
import es.upm.etsisi.commands.Command;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute() {
        Message.HELP_MESSAGE.writeln();
    }
}