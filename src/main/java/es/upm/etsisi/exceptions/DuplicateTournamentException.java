package es.upm.etsisi.exceptions;

public class DuplicateTournamentException extends DuplicateElementException {
    public DuplicateTournamentException(String element) {
        super("Tournament " + element);
    }
}
