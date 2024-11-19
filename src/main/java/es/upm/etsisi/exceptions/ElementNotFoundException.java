package es.upm.etsisi.exceptions;

public class ElementNotFoundException extends ListException {
    public ElementNotFoundException(String element) {
        super("'" + element + "' no se encuentra en la lista.");
    }
}
