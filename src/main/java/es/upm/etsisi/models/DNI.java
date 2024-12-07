package es.upm.etsisi.models;

public class DNI {
    private final String dni;

    private DNI(String dni) {
        this.dni = dni;
    }

    public static DNI valueOf(String dni) throws IllegalArgumentException {
        if (!DNI.isValidDNI(dni)) {
            throw new IllegalArgumentException("Invalid DNI format or letter");
        }
        return new DNI(dni);
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

    @Override
    public String toString() {
        return this.dni;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DNI dni = (DNI) object;
        return this.dni.equals(dni.dni);
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }
}
