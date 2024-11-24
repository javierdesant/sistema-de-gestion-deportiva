package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.Message;

public class DeletePlayerCommand extends Command {
    private final Controller controller;

    public DeletePlayerCommand(Controller controller) {
        super("player-remove", 1);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String playerName = args.pollToken();

        if (this.areInvalidTokens(playerName)) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.deletePlayer(playerName);

        if (error != null) {
            Message.PLAYER_REMOVED.writeln();
        }
        return error;
    }
}
