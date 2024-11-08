package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.auth.Administrator;
import es.upm.etsisi.models.game.Statistics;

import java.util.ArrayList;

public class Player extends Participant {
    public Player(String name, Statistics statistics, String adminName) {
        super(name, statistics, adminName);
    }

    public Player(String name, String adminName) {
        this(name, new Statistics(), adminName);
    }

    public Player(String name, Administrator administrator) {
        this(name, administrator.getUsername());
    }

    @Override
    public ArrayList<Participant> getChildren() {
        return new ArrayList<>();
    }
}