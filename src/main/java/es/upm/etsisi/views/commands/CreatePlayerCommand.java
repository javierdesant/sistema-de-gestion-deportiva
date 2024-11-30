package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.models.DNI;
import es.upm.etsisi.utils.Message;

public class CreatePlayerCommand extends Command {
    private final Controller controller;

    CreatePlayerCommand(Controller controller) {
        super("player-create", 5, "Registra un nuevo jugador en el sistema.");
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
        if (!error.isNull()) {
            return error;
        }

        error = this.controller.createPlayer(UpmEmail.valueOf(username), password, playerName, playerLastName, new DNI(dni));

        if (error.isNull()) {
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
            return this.validateUsername(username);
        }
    }

    private ErrorType validateUsername(String username) {
        try {
            UpmEmail.valueOf(username);
        } catch (IllegalArgumentException ex) {
            return ErrorType.INVALID_EMAIL;
        }
        return ErrorType.NULL;
    }
}
