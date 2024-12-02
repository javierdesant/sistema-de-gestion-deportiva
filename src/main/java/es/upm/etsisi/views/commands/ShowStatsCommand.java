package es.upm.etsisi.views.commands;

import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.Statistics;
import es.upm.etsisi.service.ErrorType;
import es.upm.etsisi.service.ParticipantManager;

public class ShowStatsCommand extends Command {
    private final ParticipantManager participantManager;

    ShowStatsCommand(ParticipantManager participantManager) {
        super("statistics-show", 0,
                "Muestra las estadísticas del jugador autenticado. Con la opción -csv, se presentan en formato tabla; con -json, en formato clave-valor.");
        this.participantManager = participantManager;
    }

    @Override
    protected ErrorType execute(CommandArguments args) { // TODO
        /*assert this.participantManager.getUser().getRole() == Role.PLAYER;

        Player player = (Player) this.participantManager.getUser();

        Statistics stats = this.participantManager.getParticipantStats(player);

        for (Category category : Category.values()) {
            System.out.println(category + "\t- " + stats.get(category));
        }
        */
        return ErrorType.NULL; 
    }
}