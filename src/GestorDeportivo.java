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
                String[] splitCommand = scanner.next().trim().split(" ");   // FIXME: No detecta los argumentos
                String commandName = splitCommand[0];
                String[] commandArgs = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
                System.out.println();

                switch (commandName) {
                    case "create":
                        if (commandArgs.length != 1) {
                            throw new Error("Argumentos no válidos");
                        }
                        playerList.add(new Player(commandArgs[0]));
                        break;
                    
                    case "remove":
                        if (commandArgs.length != 1) {
                            throw new Error("Argumentos no válidos");
                        }
                        playerList.remove(new Player(commandArgs[0]));
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
                        matchList.add(new Match(new Player(commandArgs[0]), new Player(commandArgs[1])));
                        break;

                    case "random_matchmake":
                        matchList.randomize(playerList);
                        break;

                    case "exit":
                        System.out.println("Cerrando...");
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
    

        // Código viejo
        // TODO: borrar antes de entregar

        // PlayerList playerList = new PlayerList();
        
        // playerList.create("Luisa");
        // playerList.score("Luisa", 9.0);

        // playerList.create("Manuel");
        // playerList.score("Manuel", 2.7);

        // playerList.create("Kurt");
        // playerList.score("Kurt", 4.0);

        // playerList.create("Sofia");
        // playerList.score("Sofia", 3.8);

        // playerList.create("Robert");
        // playerList.score("Robert", 3.8);

        // playerList.create("Paco");
        // playerList.score("Paco", 6.2);

        // playerList.show();

        // MatchList matchList = new MatchList();

        // matchList.randomize(playerList);
        // matchList.show_matchmake();

        // matchList.clear_matchmake();
        // matchList.show_matchmake();
        
        // matchList.randomize(playerList);
        // matchList.show_matchmake();

        // System.out.println("-----------TESTS-----------");  // TODO: Implementar tests correctamente tras cambiar la app

        // System.out.println("TEST 1. Adding existing player (Sofia):");
        // try {
        //     playerList.create("Sofia");
        //     System.out.println("This should not be printed");
        // } catch (Error error) {
        //     System.out.println("This error is correct: " + error.getMessage() + "\n");
        // }

        // try {
        //     playerList.add(new Player("Sofia"));
        //     System.out.println("This should not be printed");
        // } catch (Error error) {
        //     System.out.println("This error is correct: " + error.getMessage() + "\n");
        // }

        // System.out.println("TEST 2. Trying to randomize a MatchList with uneven players:");
        
        // Player testPlayer = new Player("Name");
        // playerList.add(testPlayer);
        // playerList.score("Name", 3.8);
        // try {    
        //     playerList.show();
        //     new MatchList().randomize(playerList);
        //     System.out.println("This should not be printed");
        // } catch (Error error) {
        //     System.out.println("This error is correct: " + error.getMessage() + "\n");
        // } finally {
        //     playerList.remove(testPlayer);
        // }

        // System.out.println("---------------------------");
    }
}
