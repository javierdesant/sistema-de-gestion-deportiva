package es.upm.etsisi.commands;

import es.upm.etsisi.exceptions.DifferingTypeException;
import es.upm.etsisi.exceptions.ListException;
import es.upm.etsisi.utils.Message;

public abstract class Command {
    private final String name;
    private final int maxArguments;
    private String[] arguments;

    public Command(String name, int maxArguments) {
        this.name = name;
        this.maxArguments = maxArguments;
    }

    public String getName() {
        return this.name;
    }

    protected String getArgument(int index) {
        assert this.arguments.length > index : Message.INVALID_ARGUMENTS;       // TODO: add exceptions
        assert index < this.maxArguments : Message.INVALID_ARGUMENTS;           // TODO: add exceptions

        return this.arguments[index];
    }

    public void validate(String input) {
        assert this.isCalled(input);

        String[] split = input.trim().split(" ");

        if (split.length > 1) {
            this.arguments = split[1].split(";");
        } else {
            this.arguments = new String[0];
        }
    }

    public boolean isCalled(String input) {
        assert input != null;

        String name = input.trim().split(" ")[0];
        return name.equals(this.name);
    }

    public abstract void execute() throws ListException, DifferingTypeException;
}
