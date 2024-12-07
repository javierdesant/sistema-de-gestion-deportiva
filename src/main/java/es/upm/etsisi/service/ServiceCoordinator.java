package es.upm.etsisi.service;

public class ServiceCoordinator {
    private ParticipantService participantService;
    private TournamentService tournamentService;

    public ServiceCoordinator(AuthenticationService authenticationService) {
        this.participantService = new ParticipantService(authenticationService, this);
        this.tournamentService = new TournamentService(authenticationService, this);
    }

    public ParticipantService getParticipantService() {
        return this.participantService;
    }

    public TournamentService getTournamentService() {
        return this.tournamentService;
    }
}
