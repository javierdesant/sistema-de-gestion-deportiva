package es.upm.etsisi.commands;

import es.upm.etsisi.*;

import java.util.Scanner;

public class RandomMatchmakeCommand extends Command {
    private final Scanner scanner;  // TODO: revisar / corregir

    public RandomMatchmakeCommand(PlayerList playerList, MatchList matchList, String[] arguments, Scanner scanner) {
        super(playerList, matchList, "random_matchmake", arguments);
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: quitar switch
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