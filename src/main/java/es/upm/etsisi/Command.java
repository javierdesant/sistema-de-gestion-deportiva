package es.upm.etsisi;

import java.util.Scanner;

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

    public void chooseCommand(PlayerList playerList, MatchList matchList) {
        assert playerList != null : "La lista de jugadores no puede ser nula";
        assert matchList != null : "La lista de emparejamientos no puede ser nula";

        this.readCommand();

        try {
            switch (this.name) {
                case "create":
                    assert this.arguments.length == 1 : "Argumentos no válidos";
                    assert this.arguments[0].matches("[a-zA-Z]+") : "Nombre no válido";
                    this.create(playerList, this.arguments[0]);
                    break;

                case "remove":
                    assert this.arguments.length == 1 : "Argumentos no válidos";
                    this.remove(matchList, playerList, this.arguments[0]);
                    break;

                case "show":
                    this.show(playerList);
                    break;

                case "rank":
                    this.rank(playerList);
                    break;

                case "score":
                    assert this.arguments.length == 2 : "Argumentos no válidos";
                    try {
                        this.score(playerList, this.arguments[0], Double.parseDouble(this.arguments[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Introduzca un número válido");
                    }
                    break;

                case "show_matchmake":
                    this.showMatchmake(matchList);
                    break;

                case "clear_matchmake":
                    this.clearMatchmake(matchList);
                    break;

                case "matchmake":
                    assert this.arguments.length == 2 : "Argumentos no válidos";
                    this.matchmake(matchList, playerList, this.arguments[0], this.arguments[1]);
                    break;

                case "random_matchmake":
                    this.randomMatchmake(matchList, playerList);
                    break;

                case "h":
                case "help":
                    this.help();
                    break;

                case "exit":
                    this.exit();
                    break;

                default:
                    System.out.println("Comando no válido. (Escriba h o help para ver el listado de comandos)");
            }
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    private void readCommand() {
        System.out.println();
        System.out.print("> ");
        String[] splitCommand = scanner.nextLine().trim().split(" ");
        this.name = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
    }

    private void create(PlayerList playerList, String playerName) {
        playerList.add(new Player(playerName));
        System.out.println("Jugador añadido con éxito.");
    }

    private void remove(MatchList matchList, PlayerList playerList, String playerName) {
        if (matchList.isMatched(playerName)) {
            System.out.println(
                    "El jugador que intenta borrar se encuentra emparejado. Borrar a este jugador supone también borrar su emparejamiento");
            System.out.print("¿Desea continuar? (S/N) ");
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    matchList.remove(playerName);
                    playerList.remove(new Player(playerName));
                    break;
                case "N":
                    System.out.println("Cancelando...");
                    break;
                default:
                    System.out.println("Opción no válida. Cancelando...");
                    break;
            }
        } else {
            playerList.remove(new Player(playerName));
        }
        System.out.println("Jugador eliminado con éxito.");
    }

    private void show(PlayerList playerList) {
        if (playerList.isEmpty()) {
            System.out.println("No hay jugadores");
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

    private void score(PlayerList playerList, String playerName, double score) {
        playerList.score(playerName, score);
        System.out.printf("La puntuación de %s ahora es %.2f.\n", playerName, score);
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
        System.out.println(
                "Esta opción creará emparejamientos aleatorios con todos los jugadores, eliminado los emparejamientos anteriores.");
        System.out.print("¿Desea continuar? (S/N) ");
        switch (scanner.nextLine().toUpperCase()) {
            case "S":
                matchList.clear();
                matchList.randomize(playerList);
                break;
            case "N":
                System.out.println("Cancelando...");
                break;
            default:
                System.out.println("Opción no válida. Cancelando...");
                break;
        }
    }

    private void help() {
        System.out.println("Comandos disponibles:");
        System.out.println("""
                help
                create [player]
                remove [player]
                show
                rank
                score [player];[score]
                show_matchmake
                clear_matchmake
                matchmake [player1];[player2]
                random_matchmake
                exit
                """);
    }

    private void exit() {
        System.out.println("Cerrando...");
    }
}
