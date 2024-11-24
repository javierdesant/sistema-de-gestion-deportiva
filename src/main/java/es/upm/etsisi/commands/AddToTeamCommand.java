package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class AddToTeamCommand extends Command {
    private final Controller controller;

    public AddToTeamCommand(Controller controller) {
        super("team-add", 2);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String playerName = args.pollToken();
        String teamName = args.pollToken();

        if (this.areInvalidTokens(playerName, teamName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.addToTeam(playerName, teamName);

        if (error == null) {
            Message.PLAYER_ADDED_TO_TEAM.writeln(playerName, teamName);
        }
        return error;
    }
}
