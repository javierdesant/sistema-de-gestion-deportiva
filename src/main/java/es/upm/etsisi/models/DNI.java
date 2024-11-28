package es.upm.etsisi.models;

import java.util.Random;

public class DNI {
    private final String dni;

    public DNI(String dni) {
        assert dni != null;
        assert DNI.isValidDNI(dni);

        this.dni = dni;
    }

    public static boolean isValidDNI(String dni) {
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number = Integer.parseInt(dni.substring(0, 8));
        char expectedLetter = letters.charAt(number % 23);

        return dni.charAt(8) == expectedLetter;
    }

    public static DNI generateDNI() {
        Random randomNumber = new Random();
        String dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int digits = 0;
        do {
            digits = randomNumber.nextInt(99999999);
        } while (digits == 0);

        String dniNumber = String.format("%08d", digits);
        return new DNI(dniNumber + dniLetters.charAt(digits % 23));
    }

    public String getValue() {
        return this.dni;
    }

    @Override
    public String toString() {
        return this.dni;
    }
}
