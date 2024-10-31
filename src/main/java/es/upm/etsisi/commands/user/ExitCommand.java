package es.upm.etsisi.commands.user;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.service.CLI;
import es.upm.etsisi.service.SportsService;
import es.upm.etsisi.utils.Message;

public class ExitCommand extends Command {
    SportsService service;

    public ExitCommand(SportsService sportsService) {
        super("exit");
        this.service = sportsService;
    }

    @Override
    public void execute() {
        Message.EXIT_MESSAGE.writeln();

        this.service.close();
    }
}