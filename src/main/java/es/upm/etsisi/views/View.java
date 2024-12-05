package es.upm.etsisi.views;

import es.upm.etsisi.utils.Console;

public abstract class View<T> {
    private final Console console;

    public View() {
        this.console = Console.getInstance();
    }

    protected void writeln(String message) {
        this.console.writeln(message);
    }

    protected void writeln(Double value) {
        this.console.writeln(value);
    }

    protected void write(String message) {
        this.console.write(message);
    }

    public abstract void display(T element);
}
    
