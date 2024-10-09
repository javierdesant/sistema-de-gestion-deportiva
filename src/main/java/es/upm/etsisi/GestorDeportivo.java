package es.upm.etsisi;

public class GestorDeportivo {
    public static void main(String[] args) {
        PlayerList playerList = new PlayerList();
        MatchList matchList = new MatchList();

        playerList.add(new Player("Luisa", 4.5));
        playerList.add(new Player("Manuel", 2.7));
        playerList.add(new Player("Kurt", 4.0));
        playerList.add(new Player("Sofia", 3.8));
        playerList.add(new Player("Robert", 3.8));

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.\n");

        System.out.println("Comandos disponibles:");
        System.out.println(" help\n" +
                " create [player]\n" +
                " remove [player]\n" +
                " show\n" +
                " rank\n" +
                " score [player];[score]\n" +
                " show_matchmake\n" +
                " clear_matchmake\n" +
                " matchmake [player1];[player2]\n" +
                " random_matchmake\n" +
                " exit");

        Command command = new Command();

        do {
            command.chooseCommand(playerList, matchList);
        } while (!command.getCommand().equals("exit"));
    }
}
