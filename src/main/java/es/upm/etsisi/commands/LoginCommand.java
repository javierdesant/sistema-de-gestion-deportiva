package es.upm.etsisi.commands;

import es.upm.etsisi.service.CommandArguments;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class LoginCommand extends Command {
    private final Controller controller;

    public LoginCommand(Controller controller) {
        super("login", 2);
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        ErrorType error;
        String username = args.pollToken();
        String password = args.pollToken();

        if (this.areInvalidTokens(username)) {
            return ErrorType.INVALID_ARGUMENTS;
        } else if (password == null) {
            return ErrorType.INVALID_ARGUMENTS;
        }

        error = this.controller.login(username, password);

        if (error == null) {
            // TODO: add message
        }
        return error;
    }
}
