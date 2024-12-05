package es.upm.etsisi.service;

import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.DNI;
import es.upm.etsisi.models.Participant;
import es.upm.etsisi.models.ParticipantList;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.Statistics;
import es.upm.etsisi.models.Team;
import es.upm.etsisi.utils.UpmEmail;

public class ParticipantService implements ParticipantManager {
    private final static ParticipantList participantList = new ParticipantList();
    private final AuthenticationService authenticator;

    public ParticipantService(AuthenticationService authenticator) {
        this.authenticator = authenticator;
    }

    public ErrorType createPlayer(UpmEmail username, String password, String firstName, String lastName, DNI dni) {
        ErrorType error;

        assert this.authenticator.getUser().getRole().equals(Role.ADMIN);
        Player player = new Player(username, password, firstName, lastName, dni,
                (Administrator) this.authenticator.getUser());

        error = participantList.add(player);
        if (error.isNull()) {
            error = this.authenticator.signIn(player);
            assert error.isNull();
        }

        return error;
    }

    public ErrorType createTeam(String teamName, DNI dni) {
        ErrorType error;

        assert this.authenticator.getUser().getRole().equals(Role.ADMIN);
        Participant player = participantList.find(dni);
        if (this.isValidPlayer(player)) {
            Team team = new Team(teamName, (Administrator) this.authenticator.getUser(), (Player) player);
            error = participantList.add(team);
            if (error.isNull()) {
                boolean removed = participantList.remove(player);
                assert removed;
            }
        } else {
            error = ErrorType.PLAYER_NOT_FOUND;
        }

        return error;
    }

    public ErrorType deletePlayer(DNI dni) {
        ErrorType error;

        Participant player = participantList.find(dni);
        if (this.isValidPlayer(player) && TournamentService.getTournaments().isFree(player)) {
            boolean userRemoved = this.authenticator.getUsers().remove(this.authenticator.getUsers().findByKey(dni));
            assert userRemoved;
            boolean participantRemoved = participantList.remove(player);
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

        Participant team = participantList.find(name);
        if (this.isValidTeam(team) && TournamentService.getTournaments().isFree(team)) {
            boolean removed = participantList.remove(team);
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

        Participant player = participantList.find(dni);
        Participant team = participantList.find(teamName);
        if (this.isValidPlayer(player) && this.isValidTeam(team)) {
            error = ((Team) team).add((Player) player);
        } else if (!this.isValidPlayer(player)) {
            error = ErrorType.PLAYER_NOT_FOUND;
        } else {
            error = ErrorType.TEAM_NOT_FOUND;
        }

        return error;
    }

    public ErrorType removeFromTeam(String teamName, DNI dni) {
        ErrorType error;

        Participant player = participantList.find(dni);
        Participant team = participantList.find(teamName);
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

    public ErrorType showStatisticsCsv() {
        assert this.authenticator.getUser().getRole() == Role.PLAYER;

        Player player = (Player) this.authenticator.getUser();

        Statistics stats = player.getStats();

        for (int i = 0; i < Category.values().length; i++) {
            System.out.print(Category.values()[i]);
            if (i < Category.values().length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        for (Category category : Category.values()) {
            System.out.print(stats.get(category) + "\t");
        }
        System.out.println();
        return ErrorType.NULL;

    }

    public ErrorType showStatisticsJson() {
        assert this.authenticator.getUser().getRole() == Role.PLAYER;

        Player player = (Player) this.authenticator.getUser();

        Statistics stats = player.getStats();

        for (Category category : Category.values()) {
            System.out.println("\"" + category + "\": \"" + stats.get(category) + "\"");
        }
        return ErrorType.NULL;
    }

    private boolean isValidPlayer(Participant participant) {
        return participant != null && !participant.hasChildren();
    }

    private boolean isValidTeam(Participant participant) {
        return participant != null && participant.hasChildren();
    }

    public static ParticipantList getParticipants() {
        return participantList;
    }

}
