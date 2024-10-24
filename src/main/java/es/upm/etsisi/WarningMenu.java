package es.upm.etsisi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WarningMenu extends Command {
    private final Scanner scanner;
    private final String commandName;
    private final String playerToOperate;
    private final Runnable defaultAction;
    private Map<String, Runnable> actions;

    public WarningMenu(PlayerList playerList, MatchList matchList, String commandName, String playerName) {
        super(playerList, matchList, commandName);
        this.scanner = new Scanner(System.in);
        this.actions = new HashMap<>();
        this.commandName = commandName;
        this.playerToOperate = playerName;
        this.defaultAction = () -> {Message.INVALID_OPTION.writeln(); Message.CANCEL.writeln();};
    }

    private void addActions(String playerName) {
        if (this.commandName.equals("random_matchmake")) {
            printRandomMatchmakeWarning();
            this.actions.put("S", () -> {
                getMatchList().clear();
                getMatchList().randomize(getPlayerList());
                Message.MATCHES_RANDOMIZED.writeln();
            });
        }
        if (this.commandName.equals("remove")) {
            printRemoveWarning();
            this.actions.put("S", () -> {
                getMatchList().remove(playerName);
                getPlayerList().remove(new Player(playerName));
                Message.PLAYER_REMOVED.writeln();
            });
        }
        this.actions.put("N", () -> Message.CANCEL.writeln());
    }

    private void printRandomMatchmakeWarning() {
        Message.RANDOM_MATCHMAKE_WARNING.writeln();
        Message.CONTINUE_PROMPT.write();
    }

    private void printRemoveWarning() {
        Message.ERASE_MATCHED_PLAYER_WARNING.writeln();
        Message.CONTINUE_PROMPT.write();
    }

    public void execute() {
        this.addActions(this.playerToOperate);
        actions.getOrDefault(this.scanner.nextLine().toUpperCase(), this.defaultAction).run();
    }
}
