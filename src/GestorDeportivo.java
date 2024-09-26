public class GestorDeportivo {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        PlayerList playerList = new PlayerList();
        playerList.create("Luisa");
        playerList.show();
        playerList.score("Luisa", 9.0);
        playerList.show();
        
    }
}
