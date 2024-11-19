package es.upm.etsisi.service;

import es.upm.etsisi.exceptions.*;
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
        this.userList = new UserList();
        this.participantList = new ParticipantList();
        this.tournamentList = new TournamentList();
    }

    public boolean login(String username, String password) {
        User user = this.userList.getByUsername(username);

        if (user != null && user.validate(password)) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        this.user = null;
    }

    private void register(User user) throws DuplicateElementException {
        if (this.userList.contains(user)) {
            throw new DuplicateUserException(user.getUsername());
        }

        this.userList.add(user);
    }

    public User getUser() {
        return this.user;
    }

    public void createPlayer(String username, String password, String firstName, String lastName, DNI dni)
            throws DuplicateElementException {
        Player player = new Player(username, password, firstName, lastName, dni, this.user);

        this.participantList.add(player);
        this.register(player);
    }

    public void createTeam(String teamName) throws DuplicateElementException {
        Team team = new Team(teamName, new Statistics(), this.user.getUsername());

        this.participantList.add(team);
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
        if (participant.getChildren().isEmpty()) {
            this.userList.remove(this.userList.getByUsername(name));
        }
        this.participantList.remove(participant);
    }
    // TODO: check if the participant or its team
    // is playing in an active tournament
    // To do that, TournamentList has to be iterable, to check every tournament
    // TODO: on team deletion, delete players/subteams also

    public void addToTeam(String playerName, String teamName) throws ElementNotFoundException, DifferingTypeException {
        Participant player = this.participantList.getByName(playerName);
        Participant team = this.participantList.getByName(teamName);

        if (player == null) {
            throw new ElementNotFoundException(playerName);
        } else if (team == null) {
            throw new ElementNotFoundException(teamName);
        }
        if (player.getChildren().isEmpty() && !team.getChildren().isEmpty()) {
            ((Team) team).add(player);
        } else {
            if (!player.getChildren().isEmpty()) {
                throw new DifferingPlayerException(playerName);
            } else if (team.getChildren().isEmpty()) {
                throw new DifferingTeamException(teamName);
            }
        }
    }

    public void removeFromTeam(String teamName, String playerName)
            throws ElementNotFoundException, DifferingTypeException {
        Participant team = this.participantList.getByName(teamName);
        Participant player = this.participantList.getByName(playerName);

        if (player == null) {
            throw new ElementNotFoundException(playerName);
        } else if (team == null) {
            throw new ElementNotFoundException(teamName);
        }
        if (player.getChildren().isEmpty() && !team.getChildren().isEmpty()) {
            ((Team) team).remove(player);
        } else {
            if (!player.getChildren().isEmpty()) {
                throw new DifferingPlayerException(playerName);
            } else if (team.getChildren().isEmpty()) {
                throw new DifferingTeamException(teamName);
            }
        }
    }

    public void createTournament(String tournamentName,
            LocalDate startDate,
            LocalDate endDate,
            Sport sport,
            League league,
            Category category) throws DuplicateElementException {

        Tournament tournament = new Tournament(tournamentName, startDate, endDate, sport, league, category);
        this.tournamentList.add(tournament);
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
