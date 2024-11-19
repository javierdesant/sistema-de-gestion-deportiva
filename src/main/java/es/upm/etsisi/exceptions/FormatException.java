package es.upm.etsisi.exceptions;

public class FormatException extends Exception {
    public FormatException(String element, String format) {
        super("'" + element + "' no es del tipo " + format);
    }
}
