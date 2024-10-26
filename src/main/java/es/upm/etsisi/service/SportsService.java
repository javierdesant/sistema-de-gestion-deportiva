package es.upm.etsisi.service;

import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.EntityList;

public class SportsService {
    private final EntityList entityList;
    private final MatchList matchList;

    public SportsService() {
        this.entityList = new EntityList();
        this.matchList = new MatchList();
    }

    public void addDefaults() {
        String[] defaultNames = {"Luisa", "Manuel", "Kurt", "Sofia", "Robert"};

        for (String defaultName : defaultNames) {
            this.entityList.add(new Player(defaultName));
        }
    }

    public void run() {
        CommandManager commandManager = new CommandManager(this.entityList, this.matchList);

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        commandManager.run();
    }
}
