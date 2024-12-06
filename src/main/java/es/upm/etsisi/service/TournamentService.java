package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.views.TournamentListView;

import java.util.*;

public class TournamentService implements TournamentManager {
    private final TournamentList tournamentList;
    private final AuthenticationService authenticator;
    private final ServiceCoordinator coordinator;

    public TournamentService(AuthenticationService authenticator, ServiceCoordinator coordinator) {
        this.authenticator = authenticator;
        this.coordinator = coordinator;
        this.tournamentList = new TournamentList();
    }

    public TournamentList getTournaments() {
        return this.tournamentList;
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

    public ErrorType tournamentMatchmake(String tournamentName, Collection<DNI> dnis) {
        Tournament tournament = this.tournamentList.find(tournamentName);
        if (tournament == null) {
            return ErrorType.TOURNAMENT_NOT_FOUND;
        } else if (!tournament.isActive()) {
            return ErrorType.TOURNAMENT_NOT_ACTIVE;
        }

        ArrayList<Participant> participants = this.coordinator.getParticipantService().getParticipants()
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
        Team team = this.coordinator.getParticipantService().getParticipants().getTeamOf((Player) this.authenticator.getUser());
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

    public ErrorType leaveTournamentAsTeam(String tournamentName) {
        ErrorType error;

        assert this.authenticator.getUser().getRole().equals(Role.PLAYER);
        Team team = this.coordinator.getParticipantService().getParticipants().getTeamOf((Player) this.authenticator.getUser());
        if (team != null) {
            error = ErrorType.NULL;
            for (Player player : team.getMembers()) {
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

    public ErrorType listTournaments() {
        if (tournamentList.isEmpty()) {
            System.out.println("No hay torneos inscritos en el sistema.");
        } else {
            LinkedList<Tournament> res = new LinkedList<>(tournamentList.getElements());
            switch (this.authenticator.getUser().getRole()) {
                case ADMIN:
                    res.removeIf(tournament -> !tournament.isActive());
                case PLAYER:
                    res.sort(Comparator.comparing(Tournament::getCategory));
                    break;
                case GUEST:
                default:
                    Collections.shuffle(res);
            }

            new TournamentListView().write(new TournamentList(res));
        }

        return ErrorType.NULL;
    }
}
