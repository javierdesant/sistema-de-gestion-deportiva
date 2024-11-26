package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    ExitCommand() {
        super("exit", 0);
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        Message.EXIT_MESSAGE.writeln();
        return null;
    }
}