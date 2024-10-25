package es.upm.etsisi;

public class App {
    public static void main(String[] args) {
        SportsService sportsService = new SportsService();

        sportsService.addDefaults();
        sportsService.run();
    }
}
