package es.upm.etsisi.service;

import java.util.Collection;
import java.util.LinkedList;

public class CommandArguments {
    LinkedList<String> tokens;
    LinkedList<String> flags;

    public CommandArguments(Collection<String> arguments) {
        this.tokens = new LinkedList<>(arguments);
        this.flags = new LinkedList<>();
        this.separateFlags();
    }

    private void separateFlags() {
        LinkedList<String> remainingTokens = new LinkedList<>();

        for (String token : this.tokens) {
            if (token.startsWith("-")) {
                this.flags.add(token);
            } else {
                remainingTokens.add(token);
            }
        }

        this.tokens = remainingTokens;
    }

    public boolean containsFlag(String flag) {
        assert flag.startsWith("-");
        return this.flags.contains(flag);
    }

    public boolean hasToken() {
        return !this.tokens.isEmpty();
    }

    public String pollToken() {
        return this.tokens.poll();
    }
}
