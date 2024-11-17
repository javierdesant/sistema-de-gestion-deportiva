package es.upm.etsisi.commands;

import es.upm.etsisi.SportsManager;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    private final SportsManager sportsManager;

    public ExitCommand(SportsManager sportsManager) {
        super("exit", 0);
        this.sportsManager = sportsManager;
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();

        this.sportsManager.close();
    }
}