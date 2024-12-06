package es.upm.etsisi.views;

import es.upm.etsisi.models.Category;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Role;
import es.upm.etsisi.models.Statistics;
import es.upm.etsisi.service.ErrorType;

public class StatisticsView extends View<Statistics> {

    @Override
    public void display(Statistics stats) {
        this.displayJson(stats);
    }

    public void displayJson(Statistics stats) {
        this.printBanner();
        this.printLine();
        for (Category category : Category.values()) {
            this.writeln("\"" + category + "\": \"" + stats.get(category) + "\"");
        }
        this.printLine();
    }


    public void displayCsv(Statistics stats) {
        this.printBanner();
        this.printLine();
        for (int i = 0; i < Category.values().length; i++) {
            this.write(Category.values()[i].toString());
            if (i < Category.values().length - 1) {
                this.write(", ");
            }
        }
        this.writeln();
        for (Category category : Category.values()) {
            this.write(stats.get(category) + "\t");
        }
        this.printLine();
    }

    private void printBanner() {
        this.writeln("     ESTADISTICAS     ");
    }
    private void printLine() {
        this.writeln("----------------------");
    }
}
