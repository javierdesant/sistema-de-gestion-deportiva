package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class DeleteTeamCommand extends Command {
    private final Controller controller;

    DeleteTeamCommand(Controller controller) {
        super("team-delete", 1, "Elimina del sistema al equipo, siempre que no esté participando en un torneo en curso.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();

        if (this.areInvalidTokens(teamName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.deleteTeam(teamName);

        if (error == null) {
            Message.TEAM_REMOVED.writeln();
        }
        return error;
    }
}
