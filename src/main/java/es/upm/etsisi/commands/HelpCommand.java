package es.upm.etsisi.commands;

import es.upm.etsisi.service.CLI;

public class HelpCommand extends Command {
    private final CLI cli;

    public HelpCommand(CLI cli) {
        super("help", 0);
        this.cli = cli;
    }

    @Override
    public void execute() {
        System.out.println("Commands:");    // TODO: add Message ?
        for (Command command : this.cli.getCommands()) {
            System.out.println(" - " + command.getName());
        }
    }
}
