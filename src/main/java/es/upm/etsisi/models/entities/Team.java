package es.upm.etsisi.models.entities;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;
import es.upm.etsisi.utils.Message;

import java.util.ArrayList;
import java.util.EnumMap;

public class Team implements Entity {   // TODO: implement
    private final String name;
    private final Statistics statistics;
    private final ArrayList<Entity> children;

    public Team(String name, Statistics statistics) {
        this.name = name;
        this.statistics = statistics;
        this.children = new ArrayList<>();
    }

    public Team(String name) {
        this(name, new Statistics());
    }

    @Override
    public String getName() {
        return this.name;
    }

    public EnumMap<Category, Double> getAllStatistics() {
        return this.statistics.getAllStatistics();
    }

    public double getStatistic(Category category) {
        return this.statistics.getStatistic(category);
    }

    @Override
    public void add(Entity entity) {
        this.children.add(entity);
    }

    @Override
    public void remove(Entity entity) {
        this.children.remove(entity);
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (Entity entity : this.children) {
            entity.show();
            Message.FOOTER.writeln();
        }
    }

    @Override
    public ArrayList<Entity> getChildren() {
        return new ArrayList<>(this.children);
    }
}