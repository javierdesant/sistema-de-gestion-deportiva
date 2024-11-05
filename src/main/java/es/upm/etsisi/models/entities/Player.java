package es.upm.etsisi.models.entities;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public class Player extends Entity {
    private final String adminName;

    public Player(String name, String adminName, Statistics statistics) {
        super(name, statistics);
        this.adminName = adminName;
    }

    public Player(String name, String adminName) {
        this(name, adminName, new Statistics());
    }

    public Player(String name, Administrator administrator) {
        this(name, administrator.getUsername());
    }

    public String getAdminName() {
        return this.adminName;
    }

    @Override
    public ArrayList<Entity> getChildren() {
        return new ArrayList<>();
    }
}