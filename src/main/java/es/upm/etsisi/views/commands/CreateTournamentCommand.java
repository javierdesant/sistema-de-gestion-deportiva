package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.*;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.TournamentService;
import es.upm.etsisi.utils.CommandFeedback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateTournamentCommand extends Command {
    private final TournamentService tournamentService;

    CreateTournamentCommand(TournamentService tournamentService) {
        super("tournament-create", 6,
                "[name;sportCode;leagueCode;categoryCode;startDate;endDate] Registra un nuevo torneo en el sistema.");
        this.tournamentService = tournamentService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        ErrorType error;
        String name = args.pollToken();
        String sportCode = args.pollToken();
        String leagueCode = args.pollToken();
        String categoryCode = args.pollToken();
        String startDate = args.pollToken();
        String endDate = args.pollToken();

        error = this.validate(name, sportCode, leagueCode, categoryCode, startDate, endDate);
        if (!error.isNull()) {
            return error;
        }
        error = this.tournamentService.createTournament(new TournamentInfo(name, Sport.getFromCode(sportCode),
                League.getFromCode(leagueCode), Category.getFromCode(categoryCode)), new TimeFrame(startDate, endDate));
        if (error.isNull()) {
            CommandFeedback.TOURNAMENT_ADDED.writeln();
        }

        return error;
    }

    private ErrorType validate(String name, String sportCode, String leagueCode, String categoryCode, String startDate, String endDate) {
        if (this.areInvalidTokens(name, sportCode, leagueCode, categoryCode, startDate, endDate)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (this.cantFindCodes(sportCode, leagueCode, categoryCode)) {
            return ErrorType.INVALID_CODE;
        } else if (this.isNotValidDateFormat(startDate) || this.isNotValidDateFormat(endDate)) {
            return ErrorType.DATE_FORMAT_ERROR;
        } else if (!TimeFrame.isValidFrame(startDate, endDate)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else {
            return ErrorType.NULL;
        }
    }

    private boolean isNotValidDateFormat(String date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        return !date.matches(dateRegex);
    }

    private boolean cantFindCodes(String sportCode, String leagueCode, String categoryCode) {
        return Sport.getFromCode(sportCode) == null ||
                League.getFromCode(leagueCode) == null ||
                Category.getFromCode(categoryCode) == null;
    }
}
