package es.upm.etsisi.commands.user;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.SportsManager;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    SportsManager sportsManager;

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