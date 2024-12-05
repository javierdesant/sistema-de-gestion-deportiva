package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantService;

public class ShowStatsCommand extends Command {
    private final ParticipantService participantService;

    ShowStatsCommand(ParticipantService participantService) {
        super("statistics-show", 1,
                "Muestra las estadísticas del jugador autenticado. Con la opción -csv, se presentan en formato tabla; con -json, en formato clave-valor.");
        this.participantService = participantService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;

        if (args.containsFlag("-csv")) {
            error = this.participantService.showStatisticsCsv();
        } else if (args.containsFlag("-json")) {
            error = this.participantService.showStatisticsJson();
        } else {
            error = ErrorType.INVALID_ARGUMENTS;
        }
        return error;
    }
}