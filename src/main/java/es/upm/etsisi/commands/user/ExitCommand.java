package es.upm.etsisi.commands.user;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.CommandManager;
import es.upm.etsisi.utils.Message;

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