package es.upm.etsisi.views;

import es.upm.etsisi.models.game.Category;
import es.upm.etsisi.models.game.Statistics;

public class StatisticsView implements View {
    private final Statistics statistics;

    public StatisticsView(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void display() {
        for (Category category : Category.values()) {
            System.out.println(category + ": " + this.statistics.get(category));
        }
    }
}
