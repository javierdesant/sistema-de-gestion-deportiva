package es.upm.etsisi.service;

import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.utils.Status;

public class SportsService {
    private final ParticipantList participantList;
    private final MatchList matchList;
    private final CLI cli;
    private Status status;

    public SportsService() {
        this.status = Status.CLOSED;
        this.participantList = new ParticipantList();
        this.matchList = new MatchList();
        this.cli = new CLI(this.participantList, this.matchList, this);
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

    public void addDefaults() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};

        for (String defaultName : defaultNames) {
            this.participantList.add(new Player(defaultName, new Administrator("default@upm.es", "")));
        }
    }

    public void run() {

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        this.cli.run();
    }
}
