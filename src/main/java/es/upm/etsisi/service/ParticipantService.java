package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.utils.UpmEmail;
import es.upm.etsisi.views.StatisticsView;

import java.util.Collection;
import java.util.Iterator;

public class ParticipantService implements ParticipantManager {
    private final static ParticipantList participantList = new ParticipantList();
    private final AuthenticationService authenticator;

    public ParticipantService(AuthenticationService authenticator) {
        this.authenticator = authenticator;
    }

    public static ParticipantList getParticipants() {
        return participantList;
    }

    public ErrorType createPlayer(UpmEmail username, String password, String firstName, String lastName, DNI dni) {
        ErrorType error;

        assert this.authenticator.getUser().getRole().equals(Role.ADMIN);
        Player player = new Player(username, password, firstName, lastName, dni, (Administrator) this.authenticator.getUser());

        error = this.addParticipant(player);
        if (error.isNull()) {
            error = this.authenticator.signIn(player);
            assert error.isNull();
        }

        return error;
    }

    private boolean isInTeam(Participant participant) {
        return this.isValidPlayer(participant) && participantList.getTeamOf((Player) participant) != null;
    }

    public ErrorType createTeam(String teamName, Collection<DNI> dnis) {
        assert dnis.size() > 1;
        ErrorType error;

        ParticipantList players = new ParticipantList();
        Iterator<DNI> iterator = dnis.iterator();
        do {
            Participant player = participantList.find(iterator.next());
            if (this.isValidPlayer(player) && !this.isInTeam(player)) {
                error = players.add(player);
            } else if (isValidPlayer(player)) {
                error = ErrorType.PLAYER_ALREADY_IN_TEAM_ERROR;
            } else {
                error = ErrorType.PLAYER_NOT_FOUND;
            }
        } while (iterator.hasNext() && error.isNull());

        if (error.isNull()) {
            error = this.addParticipant(new Team(teamName, players, (Administrator) this.authenticator.getUser()));
        }

        return error;
    }

    public ErrorType addParticipant(Participant participant) {
        assert this.isValidPlayer(participant) || this.isValidTeam(participant);
        ErrorType error;

        if (this.isInTeam(participant)) {
            error = ErrorType.PLAYER_ALREADY_IN_TEAM_ERROR;
        } else if (this.isValidTeam(participant)) {
            error = ErrorType.NULL;
            for (Player player : participant.getMembers()) {
                if (this.isInTeam(player)) {
                    error = ErrorType.PLAYER_ALREADY_IN_TEAM_ERROR;
                }
            }
        } else {
            error = participantList.add(participant);
        }

        if (error.isNull()) {
            for (Player player : participant.getMembers()) {
                participantList.remove(player);
                assert participantList.getTeamOf(player) == participant;
            }
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

        new StatisticsView().displayCsv(player.getStats());
        return ErrorType.NULL;
    }

    public ErrorType showStatisticsJson() {
        assert this.authenticator.getUser().getRole() == Role.PLAYER;

        Player player = (Player) this.authenticator.getUser();

        new StatisticsView().displayJson(player.getStats());
        return ErrorType.NULL;
    }

    private boolean isValidPlayer(Participant participant) {
        return participant != null && !participant.hasChildren();
    }

    private boolean isValidTeam(Participant participant) {
        return participant != null && participant.hasChildren();
    }

}
