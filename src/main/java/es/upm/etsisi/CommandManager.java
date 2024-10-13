package es.upm.etsisi;

import java.util.Scanner;

public class CommandManager {
    private final Scanner scanner;
    private Command command;
    private final PlayerList playerList;
    private final MatchList matchList;

    CommandManager(PlayerList playerList, MatchList matchList) {
        assert playerList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.scanner = new Scanner(System.in);
        this.playerList = playerList;
        this.matchList = matchList;
    }

    private void setCommand(Command command) {
        this.command = command;
    }

    public boolean isOpen() {
        return !this.command.getName().equals("exit");
    }

    public void chooseCommand() {
        this.readCommand();

        try {
            switch (this.command.getName()) {
                case "create":
                    this.command.create();
                    break;

                case "remove":
                    this.command.remove();
                    break;

                case "show":
                    this.command.show();
                    break;

                case "rank":
                    this.command.rank();
                    break;

                case "score":
                    this.command.score();
                    break;

                case "show_matchmake":
                    this.command.showMatchmake();
                    break;

                case "clear_matchmake":
                    this.command.clearMatchmake();
                    break;

                case "matchmake":
                    this.command.matchmake();
                    break;

                case "random_matchmake":
                    this.command.randomMatchmake();
                    break;

                case "h":
                case "help":
                    this.command.help();
                    break;

                case "exit":
                    this.command.exit();
                    this.scanner.close();
                    break;

                default:
                    Message.INVALID_COMMAND.writeln();
            }
        } catch (AssertionError error) {
            System.out.println("ERROR: " + error.getMessage());
        }
    }

    private void readCommand() {
        System.out.println();
        Message.COMMAND_PROMPT.write();

        String[] splitCommand = this.scanner.nextLine().trim().split(" ");
        String name = splitCommand[0];
        String[] arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
        this.setCommand(new Command(this.playerList, this.matchList, name, arguments));
    }
}
