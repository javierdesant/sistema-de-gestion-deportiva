package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.League;
import es.upm.etsisi.models.Sport;
import es.upm.etsisi.models.TimeFrame;
import es.upm.etsisi.models.TournamentInfo;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;

public class CreateTournamentCommand extends Command {
    private final TournamentService tournamentService;

    CreateTournamentCommand(TournamentService tournamentService) {
        super("tournament-create", 6,
                "[name;sport;league;category;startDate;endDate] Registra un nuevo torneo en el sistema.\n" +
                "\tLos códigos de Deporte, Liga y Categoría deben ser del formato correspondiente según el documento tipo_code_references en la carpeta docs.\n" +
                "\tEl formato de la fecha debe ser dd/mm/aaaa.\n");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String name = args.pollToken();
        String sport = args.pollToken();
        String league = args.pollToken();
        String category = args.pollToken();
        String startDate = args.pollToken();
        String endDate = args.pollToken();

        error = this.validate(name, sport, league, category, startDate, endDate);
        if (!error.isNull()) {
            return error;
        }
        error = this.tournamentService.createTournament(new TournamentInfo(name, Sport.getFromCode(sport),
                League.getFromCode(league), Category.getFromCode(category)), new TimeFrame(startDate, endDate));
        if (error.isNull()) {
            CommandFeedback.TOURNAMENT_ADDED.writeln();
        }

        return error;
    }

    private ErrorType validate(String name, String sport, String league, String category, String startDate,
            String endDate) {
        if (this.areInvalidTokens(name, sport, league, category, startDate, endDate)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (Sport.getFromCode(sport) == null || League.getFromCode(league) == null
                || Category.getFromCode(category) == null) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!validateDate(startDate).isNull() || !validateDate(endDate).isNull()) {
            return ErrorType.INVALID_ARGUMENTS;
        } else {
            return ErrorType.NULL;
        }
    }

    private ErrorType validateDate(String date) {
        if (!date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            return ErrorType.DATE_FORMAT_ERROR;
        } else {
            return ErrorType.NULL;
        }
    }

}
