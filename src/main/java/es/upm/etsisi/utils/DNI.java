package es.upm.etsisi.utils;

public class DNI {
    private final String dni;

    public DNI(String dni) {
        assert dni != null;
        assert !this.isValidDNI(dni) : "Invalid DNI format or checksum";    // TODO: add to Message enum

        this.dni = dni;
    }

    public String getValue() {
        return this.dni;
    }

    private boolean isValidDNI(String dni) {
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        try {
            int number = Integer.parseInt(dni.substring(0, 8));
            char expectedLetter = this.calculateDNILetter(number);

            return dni.charAt(8) == expectedLetter;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private char calculateDNILetter(int number) {
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letters.charAt(number % 23);
    }

    @Override
    public String toString() {
        return this.dni;
    }
}
