package es.upm.etsisi.views.commands;

import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class EnrollCommand extends Command {
    private final Controller controller;

    EnrollCommand(Controller controller) {
        super("tournament-add", 0, "Inscribe al jugador autenticado o a su equipo en un torneo.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {
        // TODO
        return ErrorType.NULL;
    }
}
