package es.upm.etsisi.exceptions;

public class NonExistElement extends ListException {
    public NonExistElement(String element) {
        super("'" + element + "' no se encuentra en la lista.");
    }
}
