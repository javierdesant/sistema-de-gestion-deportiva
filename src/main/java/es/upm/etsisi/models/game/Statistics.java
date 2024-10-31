package es.upm.etsisi.models.game;

import es.upm.etsisi.views.StatisticsView;

import java.util.EnumMap;

public class Statistics {
    private final StatisticsView view;
    private final EnumMap<Category, Double> statistics;

    public Statistics() {
        this.view = new StatisticsView(this);
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

    public void show() {
        this.view.display();
    }
}