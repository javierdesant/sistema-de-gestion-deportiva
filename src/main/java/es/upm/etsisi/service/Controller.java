package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.utils.UpmEmail;

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
        this.userList = new UserList(new Administrator(UpmEmail.valueOf("admin@upm.es"), "admin"));
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

    public ErrorType createPlayer(UpmEmail username, String password, String firstName, String lastName, DNI dni) {
        ErrorType error;

        assert this.user.getRole().equals(Role.ADMIN);
        Player player = new Player(username, password, firstName, lastName, dni, (Administrator) this.user);

        error = this.participantList.add(player);
        if (error.isNull()) {
            error = this.userList.add(player);
            assert error.isNull();
        }

        return error;
    }

    public ErrorType createTeam(String teamName, DNI dni) {
        assert this.user.getRole() == Role.ADMIN;
        ErrorType error;

        Participant player = this.participantList.find(dni);
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

    public ErrorType deletePlayer(DNI dni) {
        ErrorType error;

        Participant player = this.participantList.find(dni);
        if (this.isValidPlayer(player) && this.tournamentList.isFree(player)) {
            boolean userRemoved = this.userList.remove(this.userList.findByKey(dni));
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

    public ErrorType addToTeam(DNI dni, String teamName) {
        ErrorType error;

        Participant player = this.participantList.find(dni);
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

    public ErrorType createTournament(TournamentInfo tournamentInfo, TimeFrame timeFrame) {     // FIXME
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

    private ErrorType enroll(String tournamentName, Participant participant) {
        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.enroll(participant);
    }

    public ErrorType enrollUser(String tournamentName) {
        assert this.user.getRole().equals(Role.PLAYER);
        return this.enroll(tournamentName, (Player) this.user);
    }

    public ErrorType enrollTeamOfUser(String tournamentName) {
        assert this.user.getRole().equals(Role.PLAYER);
        Team team = this.participantList.getTeam((Player) this.user);
        if (team != null) {
            return this.enroll(tournamentName, team);
        } else {
            return ErrorType.TEAM_NOT_FOUND;
        }
    }

    private ErrorType leave(String tournamentName, Participant participant) {
        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.remove(participant);
    }

    public ErrorType leaveTournament(String tournamentName) {
        assert this.user.getRole().equals(Role.PLAYER);
        return this.leave(tournamentName, (Player) this.user);
    }

    // FIXME: the players go back to the list after removal
    public ErrorType leaveTournamentAsTeam(String tournamentName) {
        ErrorType error;

        assert this.user.getRole().equals(Role.PLAYER);
        Team team = this.participantList.getTeam((Player) this.user);
        if (team != null) {
            error = ErrorType.NULL;
            for (Player player : team.getChildren()) {
                error = this.leave(tournamentName, player);
                assert error.isNull();
            }
        } else {
            error = ErrorType.TEAM_NOT_FOUND;
        }

        return error;
    }

    public Statistics getParticipantStats(Participant participant) {
        return participant.getStats();
    }

    public TournamentList getTournaments() {
        return this.tournamentList;
    }
}
