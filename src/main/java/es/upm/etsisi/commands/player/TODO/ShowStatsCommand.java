package es.upm.etsisi.commands.player.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.service.Controller;

public class ShowStatsCommand extends Command {
    private final Controller controller;
    private final Participant participant;

    public ShowStatsCommand(Controller controller, Participant participant) {
        super("show", 1);
        this.controller = controller;
        this.participant = participant;
    }

    @Override
    public void execute() {
        Statistics stats = this.controller.getParticipantStats(this.participant);

        for (Category category : Category.values()) {
            System.out.println(category + "\t- " + stats.get(category));
        }
    }
}