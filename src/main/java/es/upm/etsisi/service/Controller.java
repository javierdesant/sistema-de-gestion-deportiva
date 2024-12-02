package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.utils.UpmEmail;

import java.util.Collection;

public class Controller {
    private final AuthenticationService authenticationService;
    private final ParticipantService participantService;
    private final TournamentService tournamentService;

    public Controller() {
        this.authenticationService = new AuthenticationService();
        this.participantService = new ParticipantService(authenticationService);
        this.tournamentService = new TournamentService(authenticationService);
    }
    
    public ErrorType login(String username, String password) {
        return this.authenticationService.login(username, password);
    }

    public void logout() {
        this.authenticationService.logout();
    }

    public User getUser(){
        return this.authenticationService.getUser();
    }

    public ErrorType createPlayer(UpmEmail username, String password, String firstName, String lastName, DNI dni) {
        return this.participantService.createPlayer(username, password, firstName, lastName, dni);
    }

    public ErrorType createTeam(String teamName, DNI dni) {
        return this.participantService.createTeam(teamName, dni);
    }

    public ErrorType deletePlayer(DNI dni) {
        return this.participantService.deletePlayer(dni);
    }

    public ErrorType deleteTeam(String name) {
        return this.participantService.deleteTeam(name);
    }

    public ErrorType addToTeam(DNI dni, String teamName) {
        return this.addToTeam(dni, teamName);
    }

    public ErrorType removeFromTeam(String teamName, DNI dni) {
        return this.removeFromTeam(teamName, dni);
    }

    public ErrorType createTournament(TournamentInfo tournamentInfo, TimeFrame timeFrame) {
        return this.tournamentService.createTournament(tournamentInfo, timeFrame);
    }

    public ErrorType deleteTournament(String tournamentName) {
        return this.tournamentService.deleteTournament(tournamentName);
    }

    public ErrorType tournamentMatchmake(String tournamentName, Collection<DNI> dnis) {
       return this.tournamentService.tournamentMatchmake(tournamentName, dnis);
    }

    public ErrorType tournamentMatchmake(String tournamentName, DNI... dnis) {
        return this.tournamentService.tournamentMatchmake(tournamentName, dnis);
    }

    public ErrorType tournamentRandomMatchmake(String tournamentName, int groupSize) {  // TODO: check groupSize here ?
        return this.tournamentRandomMatchmake(tournamentName, groupSize);
    }

    public ErrorType enrollUser(String tournamentName) {
        return this.tournamentService.enrollUser(tournamentName);
    }

    public ErrorType enrollTeamOfUser(String tournamentName) {
        return this.tournamentService.enrollTeamOfUser(tournamentName);
    }

    public ErrorType leaveTournament(String tournamentName) {
        return this.tournamentService.leaveTournament(tournamentName);
    }

    // FIXME: the players go back to the list after removal
    public ErrorType leaveTournamentAsTeam(String tournamentName) {
        return this.tournamentService.leaveTournamentAsTeam(tournamentName);
    }

    public Statistics getParticipantStats(Participant participant) {
        return participant.getStats();
    }
}
