package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.AuthenticationService;
import es.upm.etsisi.service.ErrorType;

public class LogoutCommand extends Command {
    private final AuthenticationService authenticationService;

    LogoutCommand(AuthenticationService authenticationService) {
        super("logout", 0, "Desautentica al usuario actualmente autenticado en el sistema.");
        this.authenticationService = authenticationService;
    }

    @Override
    protected ErrorType execute(ParsedInput args) {
        this.authenticationService.logout();

        return ErrorType.NULL;
    }
}
