import java.util.Scanner;

public class Command {
    private String name;
    private String[] arguments;
    private static final Scanner scanner = new Scanner(System.in);

    Command() {
        this.name = null;
        this.arguments = null;
    }
    public String getCommand(){
        return this.name;
    }
    public Command readCommand(){
        System.out.printf("> ");
        String[] splitCommand = scanner.nextLine().trim().split(" ");
        this.name = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
        return this;
    }
    public void chooseCommand(PlayerList playerList, MatchList matchList){
        this.readCommand();
        switch (this.name) {
            case "create":
                    assert this.arguments.length == 1;

                    playerList.add(new Player(this.arguments[0]));
                    System.out.println("Jugador añadido con éxito.");

                break;

            case "remove":
                assert this.arguments.length == 1;

                    playerList.remove(new Player(this.arguments[0]));
                    System.out.println("Jugador eliminado con éxito.");
                break;

            case "show":
                playerList.show();
                break;

            case "rank":
                playerList.rank();
                break;

            case "score":
                assert this.arguments.length == 2;
                playerList.score(this.arguments[0], Double.parseDouble(this.arguments[1]));
                System.out.println(
                        "La puntuación de " + this.arguments[0] + " ahora es " + this.arguments[1] + ".");    
                break;

            case "show_matchmake":
                matchList.show();
                break;

            case "clear_matchmake":
                matchList.clear();
                System.out.println("Los emparejamientos han sido eliminados.");
                break;

            case "matchmake":
                assert this.arguments.length == 2;

                matchList.add(new Match(playerList, new Player(this.arguments[0]), new Player(this.arguments[1])));
                System.out.println("Los jugadores " + this.arguments[0] + " y " + this.arguments[1] + " han sido emparejados correctamente.");
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
    }
}
