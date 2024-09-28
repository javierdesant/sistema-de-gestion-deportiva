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
        
        playerList.create("Luisa");
        playerList.score("Luisa", 9.0);

        playerList.create("Manuel");
        playerList.score("Manuel", 2.7);

        playerList.create("Kurt");
        playerList.score("Kurt", 4.0);

        playerList.create("Sofia");
        playerList.score("Sofia", 3.8);

        playerList.create("Robert");
        playerList.score("Robert", 3.8);

        playerList.create("Paco");  // FIXME: Este jugador no debería ser creado antes del bucle
        playerList.score("Paco", 6.2);

        playerList.show();

        MatchList matchList = new MatchList();

        matchList.randomize(playerList);
        matchList.show_matchmake();

        matchList.clear_matchmake();
        matchList.show_matchmake();
        
        matchList.randomize(playerList);
        matchList.show_matchmake();

        System.out.println("-----------TESTS-----------");  // TODO: Implementar tests correctamente tras cambiar la app

        System.out.println("TEST 1. Adding existing player (Sofia):");
        try {
            playerList.create("Sofia");
            System.out.println("This should not be printed");
        } catch (Error error) {
            System.out.println("This error is correct: " + error.getMessage() + "\n");
        }

        try {
            playerList.add(new Player("Sofia"));
            System.out.println("This should not be printed");
        } catch (Error error) {
            System.out.println("This error is correct: " + error.getMessage() + "\n");
        }

        System.out.println("TEST 2. Trying to randomize a MatchList with uneven players:");
        
        Player testPlayer = new Player("Name");
        playerList.add(testPlayer);
        playerList.score("Name", 3.8);
        try {    
            playerList.show();
            new MatchList().randomize(playerList);
            System.out.println("This should not be printed");
        } catch (Error error) {
            System.out.println("This error is correct: " + error.getMessage() + "\n");
        } finally {
            playerList.remove(testPlayer);
        }

        System.out.println("---------------------------");
    }
}
