package es.upm.etsisi.exceptions;

public class DifferingPlayerException extends DifferingTypeException {
    public DifferingPlayerException (String element){
        super(element, "Player");
    }
    
}
