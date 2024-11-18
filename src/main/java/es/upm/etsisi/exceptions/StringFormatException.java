package es.upm.etsisi.exceptions;

public class StringFormatException extends FormatException{
    public StringFormatException(String element){
        super(element, "String");
    }
}
