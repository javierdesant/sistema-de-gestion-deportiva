package es.upm.etsisi.service;

import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.player.Player;
import es.upm.etsisi.models.player.PlayerList;

public class SportsService {
    private final PlayerList playerList;
    private final MatchList matchList;

    public SportsService() {
        this.playerList = new PlayerList();
        this.matchList = new MatchList();
    }

    public void addDefaults() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};
        double[] defaultScores = {4.5, 2.7, 4.0, 3.8, 3.8};

        for (int i = 0; i < defaultNames.length; i++) {
            this.playerList.add(new Player(defaultNames[i], defaultScores[i]));
        }
    }

    public void run() {
        CommandManager commandManager = new CommandManager(this.playerList, this.matchList);

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        commandManager.run();
    }
}
