package es.upm.etsisi;

import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.service.CLI;
import es.upm.etsisi.utils.Status;

public class SportsManager {
    private final CLI cli;
    private Status status;

    public SportsManager() {
        this.status = Status.CLOSED;
        this.cli = new CLI(this);
    }

    public static void main(String[] args) {
        SportsManager sportsManager = new SportsManager();

        sportsManager.run();
    }

    public boolean isOpen() {
        return this.status == Status.OPEN;
    }

    public void open() {
        assert !this.isOpen();

        if (this.isClosed()) {
            this.cli.updateCommands();
        }

        this.status = Status.OPEN;
    }

    public boolean isClosed() {
        return this.status == Status.CLOSED;
    }

    public void close() {
        assert !this.isClosed();

        this.status = Status.CLOSED;
    }

    public void run() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};     // TODO: implement
        Administrator administrator = new Administrator("admin@upm.es", "admin");   // TODO: implement

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        this.cli.run();
    }
}
