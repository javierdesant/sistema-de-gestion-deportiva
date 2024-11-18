package es.upm.etsisi.exceptions;

public class DuplicatePlayerException extends DuplicateElementException{
    public DuplicatePlayerException(String element){
        super("Player " + element);
    }
}
