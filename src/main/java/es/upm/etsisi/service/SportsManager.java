package es.upm.etsisi.service;

import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.game.TournamentList;
import es.upm.etsisi.utils.Status;

public class SportsManager {
    private final ParticipantList participantList;
    private final TournamentList tournamentList;
    private final CLI cli;
    private Status status;

    public SportsManager() {
        this.status = Status.CLOSED;
        this.participantList = new ParticipantList();
        this.tournamentList = new TournamentList();
        this.cli = new CLI(this.participantList, this.tournamentList, this);
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
