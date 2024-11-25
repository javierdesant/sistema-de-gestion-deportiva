package es.upm.etsisi.views.commands;

import es.upm.etsisi.views.CLI;
import es.upm.etsisi.service.ErrorType;

public class HelpCommand extends Command {
    private final CLI cli;

    HelpCommand(CLI cli) {
        super("help", 0);
        this.cli = cli;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TODO
        return null;
    }
}
