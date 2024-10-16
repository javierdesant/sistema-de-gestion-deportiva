package es.upm.etsisi;

public class SportsManager {
    public static void main(String[] args) {
        PlayerList playerList = new PlayerList();
        MatchList matchList = new MatchList();

        playerList.add(new Player("Luisa", 4.5));
        playerList.add(new Player("Manuel", 2.7));
        playerList.add(new Player("Kurt", 4.0));
        playerList.add(new Player("Sofia", 3.8));
        playerList.add(new Player("Robert", 3.8));

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");

        CommandManager commandManager = new CommandManager(playerList, matchList);

        do {
            commandManager.chooseCommand();
        } while (commandManager.isOpen());
    }
}
