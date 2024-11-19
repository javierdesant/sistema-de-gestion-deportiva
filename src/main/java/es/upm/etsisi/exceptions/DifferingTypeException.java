package es.upm.etsisi.exceptions;

public class DifferingTypeException extends Exception{
    public DifferingTypeException (String element, String type){
        super("'" + element + "' no es del tipo '" + type + "'");
    }
    
}
