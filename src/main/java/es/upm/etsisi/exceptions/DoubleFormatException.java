package es.upm.etsisi.exceptions;

public class DoubleFormatException extends FormatException  {
    public DoubleFormatException(String element){
        super(element, "Double");
    }
}
