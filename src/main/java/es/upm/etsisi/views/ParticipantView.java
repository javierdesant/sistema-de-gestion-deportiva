package es.upm.etsisi.views;

import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.Participant;
import es.upm.etsisi.models.Statistics;

public class ParticipantView extends View<Participant> {
    public ParticipantView() {
        super();
    }

    @Override
    public void display(Participant participant) {

        if (participant.hasChildren()) {
            writeln("EQUIPO " + participant.getName().toUpperCase());
            writeln("----------------------");
            for (Participant player : participant.getMembers()) {
                display(player);
            }
        } else {
            this.writeln("JUGADOR " + participant.getName().toUpperCase());
        }
        Statistics statistics = participant.getStats();
        this.writeln("     ESTADISTICAS     ");
        this.writeln("----------------------");
        for (Category category : Category.values()) {
            this.write(category.name() + ": ");
            this.writeln(statistics.get(category));
        }
        this.writeln("----------------------");
    }
}
