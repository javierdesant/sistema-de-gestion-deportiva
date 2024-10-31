package es.upm.etsisi.utils;

public class DNI {
    private final String dni;

    public DNI(String dni) {
        assert dni != null;
        assert !DNI.isValidDNI(dni) : "Invalid DNI format or checksum";    // TODO: add to Message enum

        this.dni = dni;
    }

    public String getValue() {
        return this.dni;
    }

    public static boolean isValidDNI(String dni) {
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        try {
            String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
            int number = Integer.parseInt(dni.substring(0, 8));
            char expectedLetter = letters.charAt(number % 23);

            return dni.charAt(8) == expectedLetter;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.dni;
    }
}
