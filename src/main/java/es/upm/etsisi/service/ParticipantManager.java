package es.upm.etsisi.service;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.utils.UpmEmail;

import java.util.Collection;

public interface ParticipantManager {
    ErrorType createPlayer(UpmEmail username, String password, String firstName, String lastName, DNI dni);

    ErrorType createTeam(String teamName, Collection<DNI> dnis);

    ErrorType deletePlayer(DNI dni);

    ErrorType deleteTeam(String name);

    ErrorType addToTeam(DNI dni, String teamName);

    ErrorType removeFromTeam(String teamName, DNI dni);

}
