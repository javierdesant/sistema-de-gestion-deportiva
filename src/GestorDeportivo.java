import java.util.Scanner;

public class GestorDeportivo {
    public static void main(String[] args) {    
        
        /* 
            TODO!: Hay que añadir Scanner para que el usuario pueda interactuar con el programa,
            y poder introducir datos desde la consola. 
            La idea es rodear toda la app en un bucle que se ejecute hasta que el usuario decida salir. 
            No deben aparecer errores o excepciones que te hagan salir del programa, por
            lo que habrá que meter el código en un try-catch dentro del while.
        */
        
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

        System.out.println("Introduce un comando o escribe 'exit' para salir.");
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

        do {
            try {
                System.out.println();
                System.out.print(" > ");
                String[] splitCommand = scanner.nextLine().trim().split(" ");
                String commandName = splitCommand[0];
                String[] commandArgs = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
                System.out.println();

                switch (commandName) {
                    case "create":
                        if (commandArgs.length != 1) {
                            throw new Error("Argumentos no válidos");
                        }
                        playerList.add(new Player(commandArgs[0])); // TODO: mostrar algo de feedback cuando se añade un jugador
                        break;
                    
                    case "remove":
                        if (commandArgs.length != 1) {
                            throw new Error("Argumentos no válidos");
                        }
                        playerList.remove(new Player(commandArgs[0]));  // TODO: mostrar feedback cuando se borra un jugador y cuando no existe en la lista
                        break;

                    case "show":
                        playerList.show();
                        break;

                    case "rank":
                        playerList.rank();  // TODO: Implementar método rank en PlayerList
                        break;

                    case "score":
                        if (commandArgs.length != 2) {
                            throw new Error("Argumentos no válidos");
                        }
                        try {
                            playerList.score(commandArgs[0], Double.parseDouble(commandArgs[1]));
                        } catch (NumberFormatException e) {
                            throw new Error("La puntuación debe ser un número");
                        }
                        break;

                    case "show_matchmake":
                        matchList.show();
                        break;
                    
                    case "clear_matchmake":
                        matchList.clear();
                        break;

                    case "matchmake":
                        if (commandArgs.length != 2) {
                            throw new Error("Argumentos no válidos");
                        }
                        matchList.add(new Match(new Player(commandArgs[0]), new Player(commandArgs[1])));   // TODO: comprobar que los jugadores existen
                        break;                                                                              // TODO: mostrar feedback cuando se añade un emparejamiento
                                                                                                            // FIXME: se pueden añadir emparejamientos con jugadores que no existen
                                                                                                            // FIXME: se pueden añadir emparejamientos con jugadores que ya tienen emparejamiento

                    case "random_matchmake":
                        matchList.randomize(playerList);
                        // matchList.show();    TODO: deberiamos añadir show tras randomize para mostrar los emparejamientos?
                        break;

                    case "exit":
                        System.out.println("Cerrando...");
                        scanner.close();
                        exit = true;
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
                
                    default:
                        throw new Error("Comando no válido");
                }
            } catch (Error error) {
                System.out.println(error.getMessage() + ", por favor inténtalo de nuevo.");
            }
            
        } while (!exit);
    }
}
