import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println();
                System.out.print(" > ");
                String[] splitCommand = scanner.nextLine().trim().split(" ");
                String commandName = splitCommand[0];
                String[] commandArgs = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
                System.out.println();

                switch (commandName) {
                    case "create":
                        if (commandArgs.length == 1) {
                            playerList.add(new Player(commandArgs[0]));
                            System.out.println("Jugador añadido con éxito.");
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "remove":
                        if (commandArgs.length == 1) {
                            playerList.remove(new Player(commandArgs[0]));
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
                        if (commandArgs.length == 2) {
                            try {
                                playerList.score(commandArgs[0], Double.parseDouble(commandArgs[1]));
                                System.out.println(
                                        "La puntuación de " + commandArgs[0] + " ahora es " + commandArgs[1] + ".");
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
                        if (commandArgs.length == 2) {
                            matchList.add(new Match(playerList, new Player(commandArgs[0]), new Player(commandArgs[1])));
                            System.out.println("Los jugadores " + commandArgs[0] + " y " + commandArgs[1] + " han sido emparejados correctamente.");
                        } else {
                            throw new Error("Argumentos no válidos");
                        }
                        break;

                    case "random_matchmake":
                        matchList.randomize(playerList);
                        break;

                    case "exit":
                        System.out.println("Cerrando...");
                        scanner.close();
                        exit = true;
                        break;

                    case "help":
                    case "h":
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

                    default:
                        throw new Error("Comando no válido");
                }
            } catch (Error error) {
                System.out.println(error.getMessage() + ", por favor inténtelo de nuevo.");
            }
        }
    }
}
