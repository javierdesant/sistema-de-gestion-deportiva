package es.upm.etsisi.commands;

import es.upm.etsisi.service.Item;
import es.upm.etsisi.utils.Message;

public abstract class Command implements Item {
    private final String name_;
    private String name;
    private String[] arguments;

    public Command(String name) {
        this.name_ = name;
    }

    public String getName() {
        return this.name;
    }

    protected String getArgument(int index) {     // TODO: añadir un máximo de args dependiendo del comando
        assert this.arguments.length > index : Message.INVALID_ARGUMENTS;

        return this.arguments[index];
    }

    @Override
    public void validate(String input) {
        assert input != null;

        String[] split = input.split(" ");
        this.name = split[0];
        if (split.length > 1) {
            this.arguments = split[1].split(";");
        } else {
            this.arguments = new String[0];
        }
    }

    @Override
    public boolean isCalled() {
        return this.name.equals(this.name_);
    }
}
