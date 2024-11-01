package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.commands.Command;

import java.util.LinkedList;

public class HelpCommand extends Command {
    private final LinkedList<Command> commands;

    public HelpCommand(LinkedList<Command> commands) {
        super("help");
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Commands:");    // TODO: add Message
        for (Command command : commands) {
            System.out.println(command.getName());
        }
    }
}