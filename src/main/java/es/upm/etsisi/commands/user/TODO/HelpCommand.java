package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.utils.Message;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute() {
        Message.HELP_MESSAGE.writeln();
    }
}