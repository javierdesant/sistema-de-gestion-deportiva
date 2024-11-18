package es.upm.etsisi.exceptions;

public class DuplicateMatchException extends DuplicateElementException{
    public DuplicateMatchException(String element){
        super("Match " + element);
    }
    
}
