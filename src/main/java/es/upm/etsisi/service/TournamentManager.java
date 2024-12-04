package es.upm.etsisi.service;

import java.util.Collection;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.models.TimeFrame;
import es.upm.etsisi.models.TournamentInfo;

public interface TournamentManager {
    ErrorType createTournament(TournamentInfo tournamentInfo, TimeFrame timeFrame);

    ErrorType deleteTournament(String tournamentName);

    ErrorType tournamentMatchmake(String tournamentName, Collection<DNI> dnis);

    ErrorType tournamentRandomMatchmake(String tournamentName, int groupSize);

    ErrorType enrollUser(String tournamentName);

    ErrorType enrollTeamOfUser(String tournamentName);

    ErrorType leaveTournament(String tournamentName);

    ErrorType leaveTournamentAsTeam(String tournamentName);

    ErrorType listTournament();
}
