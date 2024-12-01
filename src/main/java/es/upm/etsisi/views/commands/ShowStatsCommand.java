package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.Statistics;
import es.upm.etsisi.service.Controller;
import es.upm.etsisi.service.ErrorType;

public class ShowStatsCommand extends Command {
    private final Controller controller;

    ShowStatsCommand(Controller controller) {
        super("statistics-show", 0, "Muestra las estadísticas del jugador autenticado. Con la opción -csv, se presentan en formato tabla; con -json, en formato clave-valor.");
        this.controller = controller;
    }

    @Override
    protected ErrorType execute(CommandArguments args) {    // TODO
        assert this.controller.getUser().getRole() == Role.PLAYER;

        Player player = (Player) this.controller.getUser();

        Statistics stats = this.controller.getParticipantStats(player);

        for (Category category : Category.values()) {
            System.out.println(category + "\t- " + stats.get(category));
        }

        return ErrorType.NULL;
    }
}