package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.AuthenticationService;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.utils.CommandFeedback;

public class LoginCommand extends Command {
    private final AuthenticationService authenticationService;

    LoginCommand(AuthenticationService authenticationService) {
        super("login", 2, "[username;password] Autentica al usuario en el sistema.");
        this.authenticationService = authenticationService;
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

        error = this.authenticationService.login(username, password);

        if (error.isNull()) {
           CommandFeedback.USER_LOGGED_IN.writeln(username);
        }
        return error;
    }
}
