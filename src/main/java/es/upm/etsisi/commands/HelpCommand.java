package es.upm.etsisi.commands;

import es.upm.etsisi.Message;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute() {
        Message.HELP_MESSAGE.writeln();
    }
}