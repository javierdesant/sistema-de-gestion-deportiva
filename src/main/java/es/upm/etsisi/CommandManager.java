package es.upm.etsisi;

import java.util.Scanner;

public class CommandManager {
    private String name;
    private String[] arguments;
    private static final Scanner scanner = new Scanner(System.in);

    CommandManager() {
        this.name = null;
        this.arguments = null;
    }

    public String getCommand() {
        return this.name;
    }

    public void chooseCommand(PlayerList playerList, MatchList matchList) {
        assert playerList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.readCommand();

        try {
            switch (this.name) {
                case "create":
                    assert this.arguments.length == 1 : Message.INVALID_ARGUMENTS;
                    assert this.arguments[0].matches("[a-zA-Z]+") : Message.INVALID_NAME;
                    this.create(playerList, this.arguments[0]);
                    break;

                case "remove":
                    assert this.arguments.length == 1 : Message.INVALID_ARGUMENTS;
                    this.remove(playerList, this.arguments[0]);
                    break;

                case "show":
                    this.show(playerList);
                    break;

                case "rank":
                    this.rank(playerList);
                    break;

                case "score":
                    assert this.arguments.length == 2 : Message.INVALID_ARGUMENTS;
                    try {
                        this.score(playerList, this.arguments[0], Double.parseDouble(this.arguments[1]));
                    } catch (NumberFormatException e) {
                        Message.INVALID_NUMBER.writeln();
                    }
                    break;

                case "show_matchmake":
                    this.showMatchmake(matchList);
                    break;

                case "clear_matchmake":
                    this.clearMatchmake(matchList);
                    break;

                case "matchmake":
                    assert this.arguments.length == 2 : Message.INVALID_ARGUMENTS;
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
                    Message.INVALID_COMMAND.writeln();
            }
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }

    private void readCommand() {
        System.out.println();
        System.out.print(Message.COMMAND_PROMPT);
        String[] splitCommand = scanner.nextLine().trim().split(" ");
        this.name = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
    }

    private void create(PlayerList playerList, String playerName) {
        playerList.add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }

    private void remove(PlayerList playerList, String playerName) {
        playerList.remove(new Player(playerName));
        Message.PLAYER_REMOVED.writeln();
    }

    private void show(PlayerList playerList) {
        if (playerList.isEmpty()) {
            Message.NO_PLAYERS.writeln();
        } else {
            Message.PLAYERLIST_HEADER.writeln();
            playerList.show();
            Message.FOOTER.writeln();
        }
    }

    private void rank(PlayerList playerList) {
        Message.RANKING_HEADER.writeln();
        playerList.rank();
        Message.FOOTER.writeln();
    }

    private void score(PlayerList playerList, String playerName, double score) {
        playerList.score(playerName, score);
        System.out.printf("La puntuación de %s ahora es %.2f.\n", playerName, score);   // TODO: replace with message
    }

    private void showMatchmake(MatchList matchList) {
        Message.MATCHMAKE_HEADER.writeln();
        matchList.show();
        Message.FOOTER.writeln();
    }

    private void clearMatchmake(MatchList matchList) {
        matchList.clear();
        Message.MATCHMAKE_CLEARED.writeln();
    }

    private void matchmake(MatchList matchList, PlayerList playerList, String homePlayerName,
            String visitingPlayerName) {
        matchList.add(new Match(playerList, new Player(homePlayerName), new Player(visitingPlayerName)));
        System.out.println("Los jugadores " + homePlayerName + " y " + visitingPlayerName
                + " han sido emparejados correctamente.");  // TODO: replace with message
    }

    private void randomMatchmake(MatchList matchList, PlayerList playerList) {
        Message.RANDOM_MATCHMAKE_WARNING.writeln();
        Message.CONTINUE_PROMPT.writeln();
        switch (scanner.nextLine().toUpperCase()) {
            case "S":
                matchList.clear();
                matchList.randomize(playerList);
                break;
            case "N":
                Message.CANCEL.writeln();
                break;
            default:
                Message.INVALID_OPTION.writeln();
                Message.CANCEL.writeln();
                break;
        }
    }

    private void help() {
        Message.HELP_MESSAGE.writeln();
    }

    private void exit() {
        Message.EXIT_MESSAGE.writeln();
    }
}
