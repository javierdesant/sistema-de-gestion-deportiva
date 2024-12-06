package es.upm.etsisi.views.commands;

import java.util.LinkedList;
import java.util.List;

public class ParsedInput {
    private final String title;
    private final LinkedList<String> tokens;
    private final LinkedList<String> flags;

    private ParsedInput(String input) {
        LinkedList<String> split = this.splitInput(input);
        this.title = split.poll();
        this.tokens = split;
        this.flags = new LinkedList<>();
        this.separateFlags();
    }

    public static ParsedInput parse(String input) {
        return new ParsedInput(input);
    }

    public static String getTitle(String input) {
        return input.split(" ")[0];
    }

    private LinkedList<String> splitInput(String input) {
        String[] split = input.trim().split(" ", 2);
        String title = split[0];
        String[] arguments = split.length > 1 ? split[1].split(";") : new String[0];

        String[] result = new String[arguments.length + 1];
        result[0] = title;
        System.arraycopy(arguments, 0, result, 1, arguments.length);

        return new LinkedList<>(List.of(result));
    }

    public String getTitle() {
        return this.title;
    }

    private void separateFlags() {
        for (String token : this.tokens) {
            if (token.startsWith("-")) {
                this.flags.add(token);
                this.tokens.remove(token);
            }
        }
    }

    public int size() {
        return this.tokens.size() + this.flags.size();
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
