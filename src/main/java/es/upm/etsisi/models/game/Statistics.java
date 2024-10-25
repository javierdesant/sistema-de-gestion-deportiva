package es.upm.etsisi.models.game;

import java.util.EnumMap;

public class Statistics {
    private final EnumMap<Categories, Double> statistics;

    public Statistics() {
        this.statistics = new EnumMap<>(Categories.class);
        for (Categories category : Categories.values()) {
            this.statistics.put(category, 0.0);
        }
    }

    public void setStatistic(Categories category, double value) {
        this.statistics.put(category, value);
    }

    public double getStatistic(Categories category) {
        assert this.statistics.containsKey(category) : "No se puede obtener el valor de la categoria";

        return this.statistics.get(category);
    }

    public EnumMap<Categories, Double> getAllStatistics() {
        return this.statistics;
    }

    public void writeln() {
        for (Categories category : Categories.values()) {
            System.out.println(category + ": " + getStatistic(category));
        }
    }
}