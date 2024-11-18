package es.upm.etsisi.service;

import es.upm.etsisi.exceptions.DuplicateElementException;
import es.upm.etsisi.exceptions.DuplicatePlayerException;
import es.upm.etsisi.exceptions.DuplicateTeamException;
import es.upm.etsisi.exceptions.DuplicateUserException;
import es.upm.etsisi.exceptions.NonExistElement;
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
        try {
            if (this.userList.contains(user)) {
                throw new DuplicateUserException(user.getUsername());
            } else{
                this.userList.add(user);
            }
        } catch (DuplicateUserException exception) {
            exception.toString();
            exception.printStackTrace();
        }
    }

    public User getUser() {
        return this.user;
    }

    public void createPlayer(String username, String password, String firstName, String lastName, DNI dni) throws DuplicateElementException {
        try {
            Player player = new Player(username, password, firstName, lastName, dni, this.user);
            if (this.participantList.contains(player)){
                throw new DuplicatePlayerException(player.getName());
            } else {
                this.participantList.add(player);
                register(player);
            }
        } catch (DuplicatePlayerException exception) {
            exception.toString();
            exception.printStackTrace();
        }
    }

    public void createTeam(String teamName) throws DuplicateElementException{
        try {
            Team team = new Team(teamName, new Statistics(), this.user.getUsername());
            if(participantList.contains(team)){
                throw new DuplicateTeamException(teamName);
            } else {
                this.participantList.add(team);
            }
        } catch (DuplicateTeamException exception) {
            exception.toString();
            exception.printStackTrace();
        }
    }

    public ParticipantList getParticipantList() {
        return this.participantList;
    }

    public TournamentList getTournamentList() {
        return this.tournamentList;
    }

    public void deleteParticipant(String name) throws NonExistElement {
        try {
            Participant participant = this.participantList.getByName(name);
            if (participant == null) {  
                throw new NonExistElement(name);
            } else {
                if (participant.getChildren() == null){
                    this.userList.remove(this.userList.getByUsername(name));
                }
                this.participantList.remove(participant);
            }
        } catch (NonExistElement exception) {
            exception.toString();
            exception.printStackTrace();
        }
        // TODO: check if the participant or its team
        //  is playing in an active tournament

        //To do that, TournamentList has to be iterable, to check every tournament
    }

    public void addToTeam(String playerName, String teamName) {
        Participant player = this.participantList.getByName(playerName);
        Participant team = this.participantList.getByName(teamName);

        // FIXME: get rid of instanceof (somehow) ?
        if (team instanceof Team && player instanceof Player) {
            ((Team) team).add(player);
        } else {
            // TODO: add exceptions
        }
    }

    public void removeFromTeam(String teamName, String playerName) {
        Participant team = this.participantList.getByName(teamName);
        Participant player = this.participantList.getByName(playerName);

        // FIXME: get rid of instanceof (somehow) ?
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
