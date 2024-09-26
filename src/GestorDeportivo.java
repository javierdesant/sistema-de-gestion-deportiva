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
    }
}
