package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;

public abstract class Command {
    private final String title;
    private final int maxArguments;
    private final String description;

    Command(String title, int maxArguments, String description) {
        this.title = title;
        this.maxArguments = maxArguments;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCalled(String title) {
        if (title == null) {
            return false;
        }
        return title.equals(this.title);
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

    public ErrorType execute(String input) {
        ParsedInput parsedInput = ParsedInput.parse(input);

        if (parsedInput.size() > this.maxArguments) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        return this.execute(parsedInput);
    }

    protected abstract ErrorType execute(ParsedInput args);
}
