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
        do{
                command.readCommand();
                System.out.println();

                switch (command.getCommandName()) {
                    case "create":
                        if (command.getArgumentsLength() == 1) {
                            playerList.add(new Player(command.getArgument(0)));
                            System.out.println("Jugador añadido con éxito.");
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "remove":
                        if (command.getArgumentsLength() == 1) {
                            playerList.remove(new Player(command.getArgument(0)));
                            System.out.println("Jugador eliminado con éxito.");
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "show":
                        playerList.show();
                        break;

                    case "rank":
                        playerList.rank();
                        break;

                    case "score":
                        if (command.getArgumentsLength() == 2) {
                            try {
                                playerList.score(command.getArgument(0), Double.parseDouble(command.getArgument(1)));
                                System.out.println(
                                        "La puntuación de " + command.getArgument(0) + " ahora es " + command.getArgument(1) + ".");
                            } catch (NumberFormatException e) {
                                throw new Error("La puntuación debe ser un número");
                            }
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "show_matchmake":
                        matchList.show();
                        break;

                    case "clear_matchmake":
                        matchList.clear();
                        System.out.println("Los emparejamientos han sido eliminados.");
                        break;

                    case "matchmake":
                        if (command.getArgumentsLength() == 2) {
                            matchList.add(new Match(playerList, new Player(command.getArgument(0)), new Player(command.getArgument(1))));
                            System.out.println("Los jugadores " + command.getArgument(0) + " y " + command.getArgument(1) + " han sido emparejados correctamente.");
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "random_matchmake":
                        matchList.randomize(playerList);
                        break;

                    case "help":
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
                        break;

                    case "exit": 
                        System.out.println("Cerrando...");
                        break;
                        
                    default: System.out.println("Comando no válido. (Escriba h o help para ver el listado de comandos)");
                }
        }while(!command.getCommandName().equals("exit"));
    }
}
