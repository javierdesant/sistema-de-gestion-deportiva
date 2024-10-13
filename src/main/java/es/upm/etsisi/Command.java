package es.upm.etsisi;

import java.util.Scanner;

public class Command {
    private final PlayerList playerList;
    private final MatchList matchList;
    private final String name;
    private final String[] arguments;

    Command(PlayerList playerList, MatchList matchList, String name, String[] arguments) {
        this.playerList = playerList;
        this.matchList = matchList;
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return this.name;
    }

    private String getArgument(int index) {     // TODO: añadir un máximo de args dependiendo del comando
        assert this.arguments.length > index : Message.INVALID_ARGUMENTS;

        return this.arguments[index];
    }

    public void create() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

        this.playerList.add(new Player(playerName));
        Message.PLAYER_ADDED.writeln();
    }

    public void remove() {
        String playerName = this.getArgument(0);

        if (this.matchList.isMatched(playerName)) {
            Scanner scanner = new Scanner(System.in);

            Message.ERASE_MATCHED_PLAYER_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    this.matchList.remove(playerName);
                    this.playerList.remove(new Player(playerName));
                    Message.PLAYER_REMOVED.writeln();
                    break;
                case "N":
                    Message.CANCEL.writeln();
                    break;
                default:
                    Message.INVALID_OPTION.writeln();
                    Message.CANCEL.writeln();
                    break;
            }
        } else {
            this.playerList.remove(new Player(playerName));
            Message.PLAYER_REMOVED.writeln();
        }
    }

    public void show() {
        Message.PLAYERLIST_HEADER.writeln();
        this.playerList.show();
        Message.FOOTER.writeln();
    }

    public void rank() {
        Message.RANKING_HEADER.writeln();
        this.playerList.rank();
        Message.FOOTER.writeln();
    }

    public void score() {
        try {
            String playerName = this.getArgument(0);

            double score = Double.parseDouble(this.getArgument(1));
            this.playerList.score(playerName, score);
            System.out.printf("La puntuación de %s ahora es %.2f.\n", playerName, score);   // TODO: añadir a Message enum
        } catch (NumberFormatException e) {
            Message.INVALID_NUMBER.writeln();
        }
    }

    public void showMatchmake() {
        Message.MATCHMAKE_HEADER.writeln();
        this.matchList.show();
        Message.FOOTER.writeln();
    }

    public void clearMatchmake() {
        this.matchList.clear();
        Message.MATCHMAKE_CLEARED.writeln();
    }

    public void matchmake() {
        String homePlayerName = this.getArgument(0);
        String visitingPlayerName = this.getArgument(1);

        this.matchList.add(new Match(this.playerList, new Player(homePlayerName), new Player(visitingPlayerName)));
        Message.PLAYERS_MATCHED.writeln(homePlayerName, visitingPlayerName);
    }

    public void randomMatchmake() {
        if (this.matchList.isEmpty()) {
            this.matchList.randomize(this.playerList);
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
            Scanner scanner = new Scanner(System.in);

            Message.RANDOM_MATCHMAKE_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    this.matchList.clear();
                    this.matchList.randomize(this.playerList);
                    Message.MATCHES_RANDOMIZED.writeln();
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
    }

    public void help() {
        Message.HELP_MESSAGE.writeln();
    }

    public void exit() {
        Message.EXIT_MESSAGE.writeln();
    }
}
