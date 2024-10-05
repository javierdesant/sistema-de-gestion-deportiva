public class GestorDeportivo {
    public static void main(String[] args) {
        PlayerList playerList = new PlayerList();

        playerList.add(new Player("Luisa"));
        playerList.score("Luisa", 4.5);

        playerList.add(new Player("Manuel"));
        playerList.score("Manuel", 2.7);

        playerList.add(new Player("Kurt"));
        playerList.score("Kurt", 4.0);

        playerList.add(new Player("Sofia"));
        playerList.score("Sofia", 3.8);

        playerList.add(new Player("Robert"));
        playerList.score("Robert", 3.8);

        MatchList matchList = new MatchList();

        System.out.println("\n### Bienvenido al Gestor Deportivo ###\n");

        System.out.println("Introduzca un comando o escriba 'exit' para salir.");
        System.out.println("Comandos disponibles:\n");

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
