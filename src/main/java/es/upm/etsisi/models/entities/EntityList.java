package es.upm.etsisi.models.entities;

import es.upm.etsisi.utils.Message;

import java.util.Collection;
import java.util.LinkedList;

public class EntityList {
    private final LinkedList<Entity> entities;

    public EntityList() {
        this.entities = new LinkedList<>();
    }

    public void add(Entity entity) {
        assert !this.entities.contains(entity) : Message.PLAYER_ALREADY_EXISTS_ERROR;
        this.entities.add(entity);
    }

    public void remove(Entity player) {
        boolean removed = this.entities.remove(player);
        assert removed : Message.PLAYER_DOES_NOT_EXIST_ERROR;
    }

    public void show() {
        if (this.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            for (Entity entity : this.entities) {
                entity.show();
            }
        }
    }

    public LinkedList<Entity> getEntities() {
        return this.entities;
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

    public boolean contains(Entity entity) {
        return this.entities.contains(entity);
    }

    public boolean containsAll(Collection<Entity> entities) {
        return this.entities.containsAll(entities);
    }

    public boolean isEmpty() {
        return this.entities.isEmpty();
    }

    public void rank() {
        this.show();    // TODO
    }
}
