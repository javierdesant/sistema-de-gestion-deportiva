package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.DNI;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;
import es.upm.etsisi.utils.CommandFeedback;

import java.util.LinkedList;

public class CreateTeamCommand extends Command {
    private final ParticipantService participantService;

    CreateTeamCommand(ParticipantService participantService) {
        super("team-create", 2, "[new-team;player] Añade a un jugador a un nuevo equipo.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String teamName = args.pollToken();

        if (this.areInvalidTokens(teamName)) {
            error = ErrorType.INVALID_ARGUMENTS;
        } else if (!teamName.matches("[a-zA-Z]+")) {
            error = ErrorType.NAME_FORMAT_ERROR;
        }
        else {
            error = this.createTeam(teamName, args);
        }

        if (error.isNull()) {
            CommandFeedback.TEAM_ADDED.writeln();
        }
        return error;
    }

    private ErrorType createTeam(String teamName, ParsedInput args) {
        ErrorType error = ErrorType.NULL;

        LinkedList<DNI> dnis = new LinkedList<>();
        while (args.hasToken() && error.isNull()) {
            String playerDni = args.pollToken();
            if (DNI.isValidDNI(playerDni)) {
                dnis.add(DNI.valueOf(playerDni));
            } else {
                error = ErrorType.INVALID_DNI_ERROR;
            }
        }
        if (dnis.size() < 2) {
            error = ErrorType.MORE_PLAYERS_NEEDED;
        } else {
            error = this.participantService.createTeam(teamName, dnis);
        }

        return error;
    }
}
