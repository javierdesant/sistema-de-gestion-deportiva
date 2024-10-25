package es.upm.etsisi.service;

import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.PlayerList;

public class SportsService {
    private final PlayerList playerList;
    private final MatchList matchList;

    public SportsService() {
        this.playerList = new PlayerList();
        this.matchList = new MatchList();
    }

    public void addDefaults() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};

        for (String defaultName : defaultNames) {
            this.playerList.add(new Player(defaultName));
        }
    }

    public void run() {
        CommandManager commandManager = new CommandManager(this.playerList, this.matchList);

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        commandManager.run();
    }
}
