package es.upm.etsisi.service;

import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.game.TournamentList;

public class Controller {
    private final AuthController authController;
    private final ParticipantList participantList;
    private final TournamentList tournamentList;

    public Controller() {
        this.authController = new AuthController();
        this.participantList = new ParticipantList();
        this.tournamentList = new TournamentList();
    }


}
