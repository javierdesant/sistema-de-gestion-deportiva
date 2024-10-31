package es.upm.etsisi.commands.user;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.CLI;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    CLI CLI;

    public ExitCommand(CLI CLI) {
        super("exit");
        this.CLI = CLI;
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();

        this.CLI.close();
    }
}