package es.upm.etsisi.views;

import es.upm.etsisi.utils.Console;

public abstract class View<T> {
    private final Console console;

    protected View() {
        this.console = Console.getInstance();
    }

    protected void writeln(String string) {
        this.console.writeln(string);
    }

    protected abstract void write(T element);
}
    
