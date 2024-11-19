package es.upm.etsisi.exceptions;

public class NameFormatException extends FormatException {
    public NameFormatException(String name) {
      super("El formato del nombre es inválido: '" + name + "'. Los nombres sólo deben contener letras.");
    }
}
