import java.util.Scanner;
import java.text.DecimalFormat;

public class Command {
    private String name;
    private String[] arguments;
    private static final Scanner scanner = new Scanner(System.in);

    Command() {
        this.name = null;
        this.arguments = null;
    }

    public String getCommand() {
        return this.name;
    }

    public Command readCommand() {
        System.out.printf(" > ");
        String[] splitCommand = scanner.nextLine().trim().split(" ");
        this.name = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
        return this;
    }

    public void chooseCommand(PlayerList playerList, MatchList matchList) { // TODO: handle null exceptions
        this.readCommand();
        switch (this.name) {
            case "create":
                assert this.arguments.length == 1 : "Introduzca un nombre";
                this.create(playerList, this.arguments[0]);
                break;

            case "remove":
                assert this.arguments.length == 1 : "Introduzca un jugador";
                this.remove(playerList, this.arguments[0]);
                break;

            case "show":
                this.show(playerList);
                break;

            case "rank":
                this.rank(playerList);
                break;

            case "score":
                assert this.arguments.length == 2 : "Introduzca un jugador y su puntuación a asignar";
                try {
                    this.score(playerList, this.arguments[0], Double.parseDouble(this.arguments[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Introduzca un número válido.");
                }
                break;

            case "show_matchmake":
                this.showMatchmake(matchList);
                break;

            case "clear_matchmake":
                this.clearMatchmake(matchList);
                break;

            case "matchmake":
                assert this.arguments.length == 2 : "Introduzca dos jugadores";
                this.matchmake(matchList, playerList, this.arguments[0], this.arguments[1]);
                break;

            case "random_matchmake":
                this.randomMatchmake(matchList, playerList);
                break;

            case "help":
                this.help();
                break;

            case "exit":
                this.exit();
                break;

            default:
                System.out.println("Comando no válido. (Escriba h o help para ver el listado de comandos)");
        }
    }

    private void create(PlayerList playerList, String playerName) {
        playerList.add(new Player(playerName));
        System.out.println("Jugador añadido con éxito.");
    }

    private void remove(PlayerList playerList, String playerName) {
        playerList.remove(new Player(playerName));
        System.out.println("Jugador eliminado con éxito.");
    }

    private void show(PlayerList playerList) {
        if (playerList.isEmpty()) {
            System.out.println("No hay jugadores.");
        } else {
            System.out.println("-----LISTA DE JUGADORES-----");
            playerList.show();
            System.out.println("----------------------------");
        }
    }

    private void rank(PlayerList playerList) {
        System.out.println("----------RANKING-----------");
        playerList.rank();
        System.out.println("----------------------------");
    }

    private void score(PlayerList playerList, String playerName, double score) { // FIXME: usar printf
        DecimalFormat df = new DecimalFormat("#.##");
        playerList.score(playerName, score);
        System.out.println("La puntuación de " + playerName + " ahora es " + df.format(score) + ".");
    }

    private void showMatchmake(MatchList matchList) {
        System.out.println("------EMPAREJAMIENTOS-------");
        matchList.show();
        System.out.println("----------------------------");
    }

    private void clearMatchmake(MatchList matchList) {
        matchList.clear();
        System.out.println("Los emparejamientos han sido eliminados.");
    }

    private void matchmake(MatchList matchList, PlayerList playerList, String homePlayerName,
            String visitingPlayerName) {
        matchList.add(new Match(playerList, new Player(homePlayerName), new Player(visitingPlayerName)));
        System.out.println("Los jugadores " + homePlayerName + " y " + visitingPlayerName
                + " han sido emparejados correctamente.");
    }

    private void randomMatchmake(MatchList matchList, PlayerList playerList) {
      System.out.println("Esta opción creará emparejamientos aleatorios con todos los jugadores, eliminado los emparejamientos anteriores.");
      System.out.println("¿Desea continuar? (S/N)");
      switch (scanner.nextLine()) {
          case "S":
              matchList.clear();
              matchList.randomize(playerList);
              break;
          case "N":
              break;
          default:
              System.out.println("Opción invalida");
              break;
      }
    }

    private void help() {
        System.out.println("Comandos disponibles:");
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
    }

    private void exit() {
        System.out.println("Cerrando...");
    }
}
