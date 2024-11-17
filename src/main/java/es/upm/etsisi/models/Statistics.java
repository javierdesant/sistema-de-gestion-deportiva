package es.upm.etsisi.models;

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

    public double get(Category category) {
        assert this.statistics.containsKey(category) : "No se puede obtener el valor de la categoría";

        return this.statistics.get(category);
    }
}