package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;

public class HelpCommand extends Command {
    HelpCommand() {
        super("help", 0, "Muestra este mensaje.");
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        return null;
    }
}
