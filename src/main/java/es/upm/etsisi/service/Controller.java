package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.utils.DNI;

import java.time.LocalDate;

public class Controller {
    private final UserList userList;
    private final ParticipantList participantList;
    private final TournamentList tournamentList;
    private User user;

    public Controller() {
        this.user = null;
        this.userList = new UserList(new Administrator("admin", "admin"));
        this.participantList = new ParticipantList();
        this.tournamentList = new TournamentList();
    }

    public Error login(String username, String password) {
        Error error;

        User user = this.userList.findByUsername(username);

        if (user != null && user.validate(password)) {
            this.user = user;
            error = null;
        } else if (user == null) {
            error = Error.USER_NOT_FOUND;
        } else {
            error = Error.WRONG_PASSWORD;
        }

        return error;
    }

    public void logout() {
        assert this.user != null;
        this.user = null;
    }

    public User getUser() {
        return this.user;
    }

    public Error createPlayer(String username, String password, String firstName, String lastName, DNI dni) {
        Error error;

        Player player = new Player(username, password, firstName, lastName, dni, this.user);

        error = this.participantList.add(player);
        if (error == null) {
            error = this.userList.add(player);
            assert error == null;
        }

        return error;
    }

    public Error createTeam(String teamName, String playerName) {
        Error error;

        Participant player = this.participantList.find(playerName);

        if (this.isValidPlayer(player)) {
            assert this.user.getRole() == Role.ADMIN;
            Team team = new Team(teamName, (Administrator) this.user, (Player) player);
            error = this.participantList.add(team);
            if (error == null) {
                boolean removed = this.participantList.remove(player);
                assert removed;
            }
        } else {
            error = Error.PLAYER_NOT_FOUND;
        }

        return error;
    }

    public Error deletePlayer(String name) {
        Error error;

        Participant player = this.participantList.find(name);

        if (this.isValidPlayer(player) && !this.tournamentList.isPlaying(player)) {
            boolean userRemoved = this.userList.remove(this.userList.findByPlayerName(name));
            boolean participantRemoved = this.participantList.remove(player);
            assert userRemoved && participantRemoved;
            error = null;
        } else if (!this.isValidPlayer(player)) {
            error = Error.PLAYER_NOT_FOUND;
        } else {
            error = Error.PLAYER_IN_GAME_ERROR;
        }

        return error;
    }

    public Error deleteTeam(String name) {
        Error error;

        Participant team = this.participantList.find(name);

        if (this.isValidTeam(team) && !this.tournamentList.isPlaying(team)) {
            boolean removed = this.participantList.remove(team);
            assert removed;
            error = null;
        } else if (!this.isValidTeam(team)) {
            error = Error.TEAM_NOT_FOUND;
        } else {
            error = Error.TEAM_IN_GAME_ERROR;
        }

        return error;
    }

    public Error addToTeam(String playerName, String teamName) {
        Error error;

        Participant player = this.participantList.find(playerName);
        Participant team = this.participantList.find(teamName);

        if (isValidPlayer(player) && isValidTeam(team)) {
            error = ((Team) team).add((Player) player);
        } else if (!isValidPlayer(player)) {
            error = Error.PLAYER_NOT_FOUND;
        } else {
            error = Error.TEAM_NOT_FOUND;
        }

        return error;
    }

    private boolean isValidPlayer(Participant participant) {
        return participant != null && !participant.hasChildren();
    }

    private boolean isValidTeam(Participant participant) {
        return participant != null && participant.hasChildren();
    }

    public Error removeFromTeam(String teamName, String playerName) {
        Error error;

        Participant player = this.participantList.find(playerName);
        Participant team = this.participantList.find(teamName);

        if (isValidPlayer(player) && isValidTeam(team)) {
            boolean removed = ((Team) team).remove((Player) player);
            if (removed) {
                error = null;
            } else {
                error = Error.PLAYER_NOT_IN_TEAM;
            }
        } else {
            error = Error.ELEMENT_NOT_FOUND;
        }

        return error;
    }

    public Error createTournament(String tournamentName,
                                  LocalDate startDate,
                                  LocalDate endDate,
                                  Sport sport,
                                  League league,
                                  Category category) {

        Tournament tournament = new Tournament(tournamentName, startDate, endDate, sport, league, category);
        return this.tournamentList.add(tournament);
    }

    public Error deleteTournament(String tournamentName) {
        Error error;

        Tournament tournament = this.tournamentList.find(tournamentName);

        if (tournament != null) {
            boolean removed = this.tournamentList.remove(tournament);
            assert removed;
            error = null;
        } else {
            error = Error.TOURNAMENT_NOT_FOUND;
        }

        return error;
    }

    public void tournamentMatchmake(String tournamentName, String... playerNames) {

    }

    public void tournamentRandomMatchmake(String tournamentName) {

    }

    public void addToTournament(String tournamentName, Player player) {
        // TODO: we need the players to know their team
    }

    public void removeFromTournament(String tournamentName, Player player) {

    }

    public Statistics getParticipantStats(Participant participant) {
        return participant.getStats();
    }

    public TournamentList getTournaments() {
        return this.tournamentList;
    }
}
