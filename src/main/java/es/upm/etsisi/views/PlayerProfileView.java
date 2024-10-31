package es.upm.etsisi.views;

import es.upm.etsisi.auth.PlayerProfile;
import es.upm.etsisi.utils.DNI;

import java.util.Scanner;

public class PlayerProfileView {
    private final Scanner scanner;   // FIXME: resources leak

    public PlayerProfileView() {
        scanner = new Scanner(System.in);
    }

    public PlayerProfile read(String username, String password) {

        DNI dni = this.readDNI();
        String firstName = this.readFirstName();
        String lastName = this.readLastName();

        return new PlayerProfile(username, password, dni, firstName, lastName);
    }

    private DNI readDNI() {
        String dni;
        boolean isValidDNI;

        do {
            System.out.print("Introduzca su DNI: ");
            dni = scanner.nextLine().trim();
            isValidDNI = DNI.isValidDNI(dni);
            if (!isValidDNI) {
                System.out.println("DNI inválido, vuelva a intentarlo");
            }
        } while (!isValidDNI);

        return new DNI(dni);
    }

    private String readFirstName() {
        System.out.print("Introduzca su nombre: ");

        return scanner.nextLine().trim();
    }

    private String readLastName() {
        System.out.print("Introduzca sus apellidos: ");

        return scanner.nextLine().trim();
    }
}
