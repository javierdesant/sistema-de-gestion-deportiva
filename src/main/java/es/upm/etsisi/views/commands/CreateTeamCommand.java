package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class CreateTeamCommand extends Command {
    private final Controller controller;

    public CreateTeamCommand(Controller controller) {
        super("team-create", 2);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();
        String playerName = args.pollToken();

        if (this.areInvalidTokens(teamName, playerName)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!teamName.matches("[a-zA-Z]+")) {
            return ErrorType.NAME_FORMAT_ERROR;
        }

        error = this.controller.createTeam(teamName, playerName);

        if (error == null) {
            Message.TEAM_ADDED.writeln();
        }
        return error;
    }
}
