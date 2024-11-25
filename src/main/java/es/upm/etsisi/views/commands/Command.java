package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;

import java.util.List;

public abstract class Command {
    private final String name;
    private final int maxArguments;

    public Command(String name, int maxArguments) {
        this.name = name;
        this.maxArguments = maxArguments;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCalled(String title) {
        return title.equals(this.name);
    }

    protected boolean areInvalidTokens(String... tokens) {
        assert tokens.length > 0;
        for (String token : tokens) {
            if (token == null || token.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public ErrorType execute(String[] args) {
        if (args.length > this.maxArguments) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        return this.execute(new CommandArguments(List.of(args)));
    }

    protected abstract ErrorType execute(CommandArguments args);
}