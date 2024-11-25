package es.upm.etsisi.views.commands;

import es.upm.etsisi.views.CLI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    private final CLI cli;

    ExitCommand(CLI cli) {
        super("exit", 0);
        this.cli = cli;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        this.cli.close();

        Message.EXIT_MESSAGE.writeln();
        return null;
    }
}