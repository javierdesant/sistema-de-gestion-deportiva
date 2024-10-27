package es.upm.etsisi.models.entities;

import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;

public class EntityList extends List<Entity> {
    public EntityList() {
        super();
    }

    @Override
    public void add(Entity entity) {
        assert !this.contains(entity) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.addElement(entity);
    }

    @Override
    public void remove(Entity player) {
        boolean removed = this.removeElement(player);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    @Override
    public void show() {
        if (this.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            for (Entity entity : this.getElements()) {
                entity.show();
                Message.FOOTER.writeln();
            }
        }
    }

    public void score(String playerName, double score) {    // TODO
//        assert isValidScore(score) : Message.SCORE_OUT_OF_BOUNDS_ERROR;
//        int index = this.entities.indexOf(new Player(playerName));
//        assert index != -1 : Message.PLAYER_DOES_NOT_EXIST_ERROR;
//        this.entities.get(index).setScore(score);
    }

    private boolean isValidScore(double score) {
        return -999999.0 < score && score < 999999.0;
    }

    public void rank() {
        this.show();    // TODO
    }
}
