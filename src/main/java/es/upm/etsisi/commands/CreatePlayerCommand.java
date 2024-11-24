package es.upm.etsisi.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.DNI;
import es.upm.etsisi.utils.Message;

public class CreatePlayerCommand extends Command {
    private final Controller controller;

    public CreatePlayerCommand(Controller controller) {
        super("player-create", 5);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String username = args.pollToken();
        String password = args.pollToken();
        String playerName = args.pollToken();
        String playerLastName = args.pollToken();
        String dni = args.pollToken();

        error = this.validate(username, password, playerName, playerLastName, dni);
        if (error != null) {
            return error;
        }

        error = this.controller.createPlayer(username, password, playerLastName, playerLastName, new DNI(dni));

        if (error == null) {
            Message.PLAYER_ADDED.writeln();
        }
        return error;
    }

    private ErrorType validate(String username, String password, String playerName, String playerLastName, String dni) {
        if (this.areInvalidTokens(username, playerName, playerLastName, dni)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (password == null) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (!playerName.matches("[a-zA-Z]+")) {
            return ErrorType.NAME_FORMAT_ERROR;
        } else if (!DNI.isValidDNI(dni)) {
            return ErrorType.INVALID_DNI_ERROR;
        } else {
            return null;
        }
    }
}
