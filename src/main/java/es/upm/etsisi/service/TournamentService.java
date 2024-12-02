package es.upm.etsisi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.Participant;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Team;
import es.upm.etsisi.models.TimeFrame;
import es.upm.etsisi.models.Tournament;
import es.upm.etsisi.models.TournamentInfo;
import es.upm.etsisi.models.TournamentList;

public class TournamentService implements TournamentManager {
    private final static TournamentList tournamentList = new TournamentList();
    private final AuthenticationService authenticator;

    public TournamentService(AuthenticationService authenticator) {
        this.authenticator = authenticator;
    }

    public ErrorType createTournament(TournamentInfo tournamentInfo, TimeFrame timeFrame) {
        return tournamentList.add(new Tournament(tournamentInfo, timeFrame));
    }

    public ErrorType deleteTournament(String tournamentName) {
        ErrorType error;

        Tournament tournament = TournamentService.tournamentList.find(tournamentName);
        if (tournament != null) {
            boolean removed = tournamentList.remove(tournament);
            assert removed;
            error = ErrorType.NULL;
        } else {
            error = ErrorType.TOURNAMENT_NOT_FOUND;
        }

        return error;
    }

    public ErrorType tournamentMatchmake(String tournamentName, Collection<DNI> dnis) {
        Tournament tournament = tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        ArrayList<Participant> participants = ParticipantService.getParticipants()
                .findAll(Collections.singleton(dnis));
        if (participants == null) {
            return ErrorType.PARTICIPANT_NOT_FOUND;
        }

        return tournament.matchmake(participants);
    }

    public ErrorType tournamentMatchmake(String tournamentName, DNI... dnis) {
        return this.tournamentMatchmake(tournamentName, Arrays.asList(dnis));
    }

    public ErrorType tournamentRandomMatchmake(String tournamentName, int groupSize) {
        Tournament tournament = tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.randomMatchmake(groupSize);
    }

    public ErrorType enrollUser(String tournamentName) {
        assert this.authenticator.getUser().getRole().equals(Role.PLAYER);
        return this.enroll(tournamentName, (Player) this.authenticator.getUser());
    }

    public ErrorType enrollTeamOfUser(String tournamentName) {
        assert this.authenticator.getUser().getRole().equals(Role.PLAYER);
        Team team = ParticipantService.getParticipants().getTeam((Player) this.authenticator.getUser());
        if (team != null) {
            return this.enroll(tournamentName, team);
        } else {
            return ErrorType.TEAM_NOT_FOUND;
        }
    }

    private ErrorType enroll(String tournamentName, Participant participant) {
        Tournament tournament = tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.enroll(participant);
    }

    public ErrorType leaveTournament(String tournamentName) {
        assert this.authenticator.getUser().getRole().equals(Role.PLAYER);
        return this.leave(tournamentName, (Player) this.authenticator.getUser());
    }

    // FIXME: the players go back to the list after removal
    public ErrorType leaveTournamentAsTeam(String tournamentName) {
        ErrorType error;

        assert this.authenticator.getUser().getRole().equals(Role.PLAYER);
        Team team = ParticipantService.getParticipants().getTeam((Player) this.authenticator.getUser());
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

    private ErrorType leave(String tournamentName, Participant participant) {
        Tournament tournament = tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        return tournament.remove(participant);
    }

    public static TournamentList getTournaments() {
        return tournamentList;
    }

}
