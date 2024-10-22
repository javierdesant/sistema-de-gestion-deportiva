package es.upm.etsisi;

public class App {
    public static void main(String[] args) {
        SportsManager sportsManager = new SportsManager();

        sportsManager.addDefaults();
        sportsManager.run();
    }
}
