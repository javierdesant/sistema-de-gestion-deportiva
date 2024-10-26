package es.upm.etsisi.models.game;

import java.util.EnumMap;

public class Statistics {
    private final EnumMap<Category, Double> statistics;

    public Statistics() {
        this.statistics = new EnumMap<>(Category.class);
        for (Category category : Category.values()) {
            this.statistics.put(category, 0.0);
        }
    }

    public void setStatistic(Category category, double value) {
        this.statistics.put(category, value);
    }

    public double getStatistic(Category category) {
        assert this.statistics.containsKey(category) : "No se puede obtener el valor de la categoria";

        return this.statistics.get(category);
    }

    public EnumMap<Category, Double> getAllStatistics() {
        return this.statistics;
    }

    public void writeln() {
        for (Category category : Category.values()) {
            System.out.println(category + ": " + getStatistic(category));
        }
    }
}