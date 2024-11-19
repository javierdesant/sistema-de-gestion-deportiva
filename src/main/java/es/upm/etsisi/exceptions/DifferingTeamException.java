package es.upm.etsisi.exceptions;

public class DifferingTeamException extends DifferingTypeException {
    public DifferingTeamException (String element){
        super(element, "Team");
    }
}
