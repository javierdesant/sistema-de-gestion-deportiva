package es.upm.etsisi.service;

public class ErrorView {
    private final Console console;
    private final Error error;
    public static final String[] MESSAGES = {
            "El elemento ya está en la lista",
            "El elemento no se encuentra en la lista",
            "Los nombres sólo pueden contener letras",
            "Comando no válido",};

    public ErrorView(Error error) {
        this.console = new Console();
        this.error = error;
    }

    public void writeln() {
        this.console.writeln(ErrorView.MESSAGES[this.error.ordinal()]);
    }
}