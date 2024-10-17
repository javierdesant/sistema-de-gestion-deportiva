package es.upm.etsisi;

import java.util.Scanner;

public class CommandManager {
    private final Scanner scanner;
    private OldCommand oldCommand;
    private final PlayerList playerList;
    private final MatchList matchList;

    CommandManager(PlayerList playerList, MatchList matchList) {
        assert playerList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.scanner = new Scanner(System.in);
        this.playerList = playerList;
        this.matchList = matchList;
    }

    private void setCommand(OldCommand oldCommand) {
        this.oldCommand = oldCommand;
    }

    public boolean isOpen() {
        return !this.oldCommand.getName().equals("exit");
    }

    public void chooseCommand() {
        this.readCommand();

        try {
            switch (this.oldCommand.getName()) {
                case "create":
                    this.oldCommand.create();
                    break;

                case "remove":
                    this.oldCommand.remove(scanner);
                    break;

                case "show":
                    this.oldCommand.show();
                    break;

                case "rank":
                    this.oldCommand.rank();
                    break;

                case "score":
                    this.oldCommand.score();
                    break;

                case "show_matchmake":
                    this.oldCommand.showMatchmake();
                    break;

                case "clear_matchmake":
                    this.oldCommand.clearMatchmake();
                    break;

                case "matchmake":
                    this.oldCommand.matchmake();
                    break;

                case "random_matchmake":
                    this.oldCommand.randomMatchmake(scanner);
                    break;

                case "h":
                case "help":
                    this.oldCommand.help();
                    break;

                case "exit":
                    this.oldCommand.exit();
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
        this.setCommand(new OldCommand(this.playerList, this.matchList, name, arguments));
    }
}
