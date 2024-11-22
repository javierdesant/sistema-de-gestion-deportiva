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

        User user = this.userList.getByUsername(username);

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

        Participant player = this.participantList.getByName(playerName);

        if (this.isValidPlayer(player)) {
            assert this.user.getRole() == Role.ADMIN;
            Team team = new Team(teamName, (Administrator) this.user, (Player) player);
            error = this.participantList.add(team);
        } else {
            error = Error.PLAYER_NOT_FOUND;
        }

        return error;
    }

    public ParticipantList getParticipantList() {
        return this.participantList;
    }

    public TournamentList getTournamentList() {
        return this.tournamentList;
    }

    public void deleteParticipant(String name) throws ElementNotFoundException {
        Participant participant = this.participantList.getByName(name);
        if (participant == null) {
            throw new ElementNotFoundException(name);
        }

        // TODO: check if the participant or its team
        //  is playing in an active tournament
        //  To do that, TournamentList has to be iterable, to check every tournament
        // TODO: on team deletion, delete players/subteams also ?

        if (participant.getChildren().isEmpty()) {
            this.userList.remove(this.userList.getByUsername(name));
        }
        boolean removed = this.participantList.remove(participant);
        assert removed;
    }

    public Error addToTeam(String playerName, String teamName) {
        Error error;

        Participant player = this.participantList.getByName(playerName);
        Participant team = this.participantList.getByName(teamName);

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

        Participant player = this.participantList.getByName(playerName);
        Participant team = this.participantList.getByName(teamName);

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

    public void deleteTournament(String tournamentName) throws ElementNotFoundException {
        Tournament tournament = this.tournamentList.getByName(tournamentName);

        if (tournament == null) {
            throw new ElementNotFoundException(tournamentName);
        }

        boolean removed = this.tournamentList.remove(tournament);
        assert removed;
        // TODO: handle possibles bugs with teams and players of the removed tournament
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
