package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.CommandFeedback;

public class ExitCommand extends Command {
    ExitCommand() {
        super("exit", 0, "Cierra el programa en curso.");
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        CommandFeedback.EXIT_MESSAGE.writeln();
        return ErrorType.NULL;
    }
}