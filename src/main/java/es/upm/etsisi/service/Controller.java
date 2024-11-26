package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.models.DNI;
import es.upm.etsisi.models.TimeFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Controller {
    private final UserList userList;
    private final ParticipantList participantList;
    private final TournamentList tournamentList;
    private User user;

    public Controller() {
        this.user = Guest.getInstance();
        this.userList = new UserList(new Administrator("admin@upm.es", "admin"));
        this.participantList = new ParticipantList();
        this.tournamentList = new TournamentList();
    }

    public ErrorType login(String username, String password) {
        ErrorType error;

        User user = this.userList.findByUsername(username);
        if (user != null && user.validate(password)) {
            this.user = user;
            error = ErrorType.NULL;
        } else if (user == null) {
            error = ErrorType.USER_NOT_FOUND;
        } else {
            error = ErrorType.WRONG_PASSWORD;
        }

        return error;
    }

    public void logout() {
        this.user = Guest.getInstance();
    }

    public User getUser() {
        return this.user;
    }

    public ErrorType createPlayer(String username, String password, String firstName, String lastName, DNI dni) {
        ErrorType error;

        Player player = new Player(username, password, firstName, lastName, dni, this.user);

        error = this.participantList.add(player);
        if (error.isNull()) {
            error = this.userList.add(player);
            assert error.isNull();
        }

        return error;
    }

    public ErrorType createTeam(String teamName, String playerName) {
        assert this.user.getRole() == Role.ADMIN;
        ErrorType error;

        Participant player = this.participantList.find(playerName);
        if (this.isValidPlayer(player)) {
            Team team = new Team(teamName, (Administrator) this.user, (Player) player);
            error = this.participantList.add(team);
            if (error.isNull()) {
                boolean removed = this.participantList.remove(player);
                assert removed;
            }
        } else {
            error = ErrorType.PLAYER_NOT_FOUND;
        }

        return error;
    }

    public ErrorType deletePlayer(String name) {
        ErrorType error;

        Participant player = this.participantList.find(name);
        if (this.isValidPlayer(player) && this.tournamentList.isFree(player)) {
            boolean userRemoved = this.userList.remove(this.userList.findByPlayerName(name));
            assert userRemoved;
            boolean participantRemoved = this.participantList.remove(player);
            assert participantRemoved;
            error = ErrorType.NULL;
        } else if (!this.isValidPlayer(player)) {
            error = ErrorType.PLAYER_NOT_FOUND;
        } else {
            error = ErrorType.PLAYER_IN_GAME_ERROR;
        }

        return error;
    }

    public ErrorType deleteTeam(String name) {
        ErrorType error;

        Participant team = this.participantList.find(name);
        if (this.isValidTeam(team) && this.tournamentList.isFree(team)) {
            boolean removed = this.participantList.remove(team);
            assert removed;
            error = ErrorType.NULL;
        } else if (!this.isValidTeam(team)) {
            error = ErrorType.TEAM_NOT_FOUND;
        } else {
            error = ErrorType.TEAM_IN_GAME_ERROR;
        }

        return error;
    }

    public ErrorType addToTeam(String playerName, String teamName) {
        ErrorType error;

        Participant player = this.participantList.find(playerName);
        Participant team = this.participantList.find(teamName);
        if (this.isValidPlayer(player) && this.isValidTeam(team)) {
            error = ((Team) team).add((Player) player);
        } else if (!this.isValidPlayer(player)) {
            error = ErrorType.PLAYER_NOT_FOUND;
        } else {
            error = ErrorType.TEAM_NOT_FOUND;
        }

        return error;
    }

    private boolean isValidPlayer(Participant participant) {
        return participant != null && !participant.hasChildren();
    }

    private boolean isValidTeam(Participant participant) {
        return participant != null && participant.hasChildren();
    }

    public ErrorType removeFromTeam(String teamName, String playerName) {
        ErrorType error;

        Participant player = this.participantList.find(playerName);
        Participant team = this.participantList.find(teamName);
        if (this.isValidPlayer(player) && this.isValidTeam(team)) {
            boolean removed = ((Team) team).remove((Player) player);
            if (removed) {
                error = ErrorType.NULL;
            } else {
                error = ErrorType.PLAYER_NOT_IN_TEAM;
            }
        } else {
            error = ErrorType.ELEMENT_NOT_FOUND;
        }

        return error;
    }

    public ErrorType createTournament(TournamentInfo tournamentInfo, TimeFrame timeFrame) {
        return this.tournamentList.add(new Tournament(tournamentInfo, timeFrame));
    }

    public ErrorType deleteTournament(String tournamentName) {
        ErrorType error;

        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament != null) {
            boolean removed = this.tournamentList.remove(tournament);
            assert removed;
            error = ErrorType.NULL;
        } else {
            error = ErrorType.TOURNAMENT_NOT_FOUND;
        }

        return error;
    }

    public ErrorType tournamentMatchmake(String tournamentName, Collection<String> participantNames) {
        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        ArrayList<Participant> participants = this.participantList.findAll(participantNames);
        if (participants == null) {
            return ErrorType.PARTICIPANT_NOT_FOUND;
        }

        return tournament.matchmake(participants);
    }

    public ErrorType tournamentMatchmake(String tournamentName, String... participantNames) {
        return this.tournamentMatchmake(tournamentName, Arrays.asList(participantNames));
    }

    public ErrorType tournamentRandomMatchmake(String tournamentName, int groupSize) {  // TODO: check groupSize here ?
        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.randomMatchmake(groupSize);
    }

    public void addToTournament(String tournamentName, String playerName) {

    }

    public void removeFromTournament(String tournamentName, String playerName) {

    }

    public Statistics getParticipantStats(Participant participant) {
        return participant.getStats();
    }

    public TournamentList getTournaments() {
        return this.tournamentList;
    }
}
