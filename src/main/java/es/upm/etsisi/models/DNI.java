package es.upm.etsisi.models;

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

    public String getValue() {
        return this.dni;
    }

    @Override
    public String toString() {
        return this.dni;
    }

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        DNI dni = (DNI) object;
        return this.dni.equals(dni.dni);
    }
}
