package es.upm.etsisi.exceptions;

import es.upm.etsisi.utils.DNI;

public class DniFormatException extends FormatException {
    public DniFormatException(DNI dni) {
        super("El formato del DNI proporcionado es inválido: " + dni.toString());
    }
}
