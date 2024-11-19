package es.upm.etsisi.exceptions;

public class DuplicateElementException extends ListException {
    public DuplicateElementException(String element) {
        super("'" + element + "' ya está en la lista.");
    }
}
