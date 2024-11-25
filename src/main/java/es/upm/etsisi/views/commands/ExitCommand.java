package es.upm.etsisi.views.commands;

import es.upm.etsisi.SportsManager;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    private final SportsManager sportsManager;

    ExitCommand(SportsManager sportsManager) {
        super("exit", 0);
        this.sportsManager = sportsManager;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        this.sportsManager.close();

        Message.EXIT_MESSAGE.writeln();
        return null;
    }
}