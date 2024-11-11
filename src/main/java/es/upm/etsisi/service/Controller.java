package es.upm.etsisi.service;

import es.upm.etsisi.models.auth.User;
import es.upm.etsisi.models.auth.UserList;
import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.Team;
import es.upm.etsisi.models.game.*;

import java.time.LocalDate;

public class Controller {
    private User user;
    private final UserList userList;
    private final ParticipantList participantList;
    private final TournamentList tournamentList;

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

    private void register(User user) {
        this.userList.add(user);
    }

    public void createPlayer(String playerName) {
        Player newPlayer = new Player(playerName, new Statistics(), this.user.getUsername());

        // TODO: make players and users the same thing
        //  this.register(newPlayer);
        this.participantList.add(newPlayer);
    }

    public void createTeam(String teamName) {
        this.participantList.add(new Team(teamName, new Statistics(), this.user.getUsername()));
    }

    public void deleteParticipant(String name) {
        Participant participant = this.participantList.getByName(name);
        if (participant == null) {
            return;     // TODO: add exceptions
        }
        // TODO: check if the participant or its team
        //  is playing in an active tournament
        // TODO: delete Players account also
        // TODO: on team deletion, delete players also ?
        this.participantList.remove(participant);
    }

    public void addToTeam(String teamName, String playerName) {
        Participant team = this.participantList.getByName(teamName);
        Participant player = this.participantList.getByName(playerName);

        // FIXME: get rid of instanceof (somehow)
        if (team instanceof Team && player instanceof Player) {
            ((Team) team).add(player);
        } else {
            // TODO: add exceptions
        }
    }

    public void removeFromTeam(String teamName, String playerName) {
        Participant team = this.participantList.getByName(teamName);
        Participant player = this.participantList.getByName(playerName);

        // FIXME: get rid of instanceof
        if (team instanceof Team && player instanceof Player) {
            ((Team) team).remove(player);
        } else {
            // TODO: add exceptions
        }
    }

    public void createTournament(String tournamentName,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 Sport sport,
                                 League league,
                                 Category category) {

        Tournament tournament = new Tournament(tournamentName, startDate, endDate, sport, league, category);
        assert !this.tournamentList.getElements().contains(tournament);  // FIXME: replace with exception

        this.tournamentList.add(tournament);
    }

    public void deleteTournament(String tournamentName) {

    }

    public void tournamentMatchmake(String tournamentName, String ...playerNames) {

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
