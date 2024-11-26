package es.upm.etsisi;

import es.upm.etsisi.views.CLI;

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
        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        this.cli.run();
    }
}
