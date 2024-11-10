package es.upm.etsisi.views;

import es.upm.etsisi.models.entities.Participant;

public class EntityView implements ModelView {
    private final Participant participant;

    public EntityView(Participant participant) {
        this.participant = participant;
    }

    @Override
    public void display() {
        System.out.println(this.participant.getName());
        this.participant.getStats().show();
        for (Participant child : this.participant.getChildren()) {
            child.show();
        }
    }
}
