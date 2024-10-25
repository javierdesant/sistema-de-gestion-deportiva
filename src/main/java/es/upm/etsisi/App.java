package es.upm.etsisi;

import es.upm.etsisi.service.SportsService;

public class App {
    public static void main(String[] args) {
        SportsService sportsService = new SportsService();

        sportsService.addDefaults();
        sportsService.run();
    }
}
