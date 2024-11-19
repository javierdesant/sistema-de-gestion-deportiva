package es.upm.etsisi.exceptions;

public class DuplicateUserException extends DuplicateElementException{
    public DuplicateUserException(String element){
        super("User " + element);
    }
}