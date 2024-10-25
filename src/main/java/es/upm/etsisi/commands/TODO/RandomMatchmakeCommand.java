package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;
import es.upm.etsisi.commands.Command;

import java.util.Scanner;

public class RandomMatchmakeCommand extends Command {
    private final PlayerList playerList;
    private final MatchList matchList;
    private final Scanner scanner;

    public RandomMatchmakeCommand(PlayerList playerList, MatchList matchList, Scanner scanner) {
        super("random_matchmake");
        this.playerList = playerList;
        this.matchList = matchList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: create warning menu class
        if (this.matchList.isEmpty()) {
            this.matchList.randomize(this.playerList);
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
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
}