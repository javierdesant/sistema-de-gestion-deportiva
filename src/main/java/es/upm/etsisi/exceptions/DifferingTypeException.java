package es.upm.etsisi.exceptions;

public abstract class DifferingTypeException extends Exception {
    public DifferingTypeException(String element, String type) {
        super("'" + element + "' no es un " + type + ".");
    }
}
