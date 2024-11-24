package es.upm.etsisi.commands;

import es.upm.etsisi.service.CLI;
import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.ErrorType;

public class HelpCommand extends Command {
    private final CLI cli;

    public HelpCommand(CLI cli) {
        super("help", 0);
        this.cli = cli;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TODO
        return null;
    }
}
