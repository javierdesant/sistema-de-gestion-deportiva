package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

import java.util.Scanner;

public class RandomMatchmakeCommand extends Command {
    private final Scanner scanner;  // TODO: revisar / corregir

    public RandomMatchmakeCommand(PlayerList playerList, MatchList matchList, Scanner scanner) {
        super(playerList, matchList, "random_matchmake");
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: create warning menu class
        if (getMatchList().isEmpty()) {
            getMatchList().randomize(getPlayerList());
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
            Message.RANDOM_MATCHMAKE_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    getMatchList().clear();
                    getMatchList().randomize(getPlayerList());
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