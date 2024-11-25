package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class RemoveFromTeamCommand extends Command {
    private final Controller controller;

    public RemoveFromTeamCommand(Controller controller) {
        super("team-remove", 2);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String teamName = args.pollToken();
        String playerName = args.pollToken();

        if (this.areInvalidTokens(teamName, playerName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.removeFromTeam(teamName, playerName);

        if (error == null) {
            Message.PLAYER_REMOVED_FROM_TEAM.writeln(playerName, teamName);
        }
        return error;
    }
}
