package es.upm.etsisi.commands;

import es.upm.etsisi.CommandManager;
import es.upm.etsisi.Message;

public class ExitCommand extends Command {
    CommandManager manager;

    public ExitCommand(CommandManager manager) {
        super("exit");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();

        this.manager.close();
    }
}