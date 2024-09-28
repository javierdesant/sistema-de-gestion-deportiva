public class GestorDeportivo {
    public static void main(String[] args) {
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

        playerList.show();

        MatchList matchList = new MatchList();

        try {
            matchList.randomize(playerList);
        } catch (Error error) {
            System.out.println("This error is correct: " + error.getMessage() + "\n");
        }

        playerList.create("Paco");
        playerList.score("Paco", 3.8);

        matchList.randomize(playerList);
        matchList.show_matchmake();

        matchList.clear_matchmake();
        matchList.show_matchmake();
        
        matchList.randomize(playerList);
        matchList.show_matchmake();
    }
}
