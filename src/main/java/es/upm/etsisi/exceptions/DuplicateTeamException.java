package es.upm.etsisi.exceptions;

public class DuplicateTeamException extends DuplicateElementException {
    public DuplicateTeamException(String element) {
        super("Team " + element);
    }
}
