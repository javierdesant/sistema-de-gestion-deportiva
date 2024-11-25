package es.upm.etsisi;

import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.service.CLI;

public class SportsManager {
    private final CLI cli;

    public SportsManager() {
        this.cli = new CLI();
    }

    public static void main(String[] args) {
        SportsManager sportsManager = new SportsManager();

        sportsManager.run();
    }

    public void run() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};     // TODO: implement
        Administrator administrator = new Administrator("admin@upm.es", "admin");   // TODO: implement

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        this.cli.run();
    }
}
