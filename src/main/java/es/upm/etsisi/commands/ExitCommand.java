package es.upm.etsisi.commands;

import es.upm.etsisi.Message;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();
    }
}