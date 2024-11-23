package es.upm.etsisi.commands;

import es.upm.etsisi.service.Error;
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
        // TODO: check for invalid arguments before calling
        assert this.arguments.length > index;
        assert index < this.maxArguments;

        return this.arguments[index];
    }

    public void validate(String input) {
        assert this.isCalled(input);

        String[] split = this.split(input);

        if (split.length > 1) {
            this.arguments = split[1].split(";");
        } else {
            this.arguments = new String[0];
        }
    }

    public boolean isCalled(String input) {
        assert input != null;

        String name = this.split(input)[0];
        return name.equals(this.name);
    }

    private String[] split(String input) {
        return input.trim().split(" ", 2);
    }

    public abstract Error execute();
}
