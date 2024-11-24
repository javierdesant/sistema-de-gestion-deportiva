package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.ErrorType;

import java.util.Collection;
import java.util.Iterator;
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
        boolean areValid = true;
        List<String> tokensList = List.of(tokens);

        Iterator<String> iterator = tokensList.iterator();
        while (iterator.hasNext() && areValid) {
            String token = iterator.next();
            areValid = token != null && !token.trim().isEmpty();
        }

        return !areValid;
    }

    public ErrorType execute(Collection<String> args) {
        if (args.size() > this.maxArguments) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        return this.execute(new CommandArguments(args));
    }

    protected abstract ErrorType execute(CommandArguments args);
}
