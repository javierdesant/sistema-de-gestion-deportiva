package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.Player;
import es.upm.etsisi.PlayerList;

import java.util.Scanner;

public class RemoveCommand extends Command {
    private final PlayerList playerList;
    private final MatchList matchList;

    public RemoveCommand(PlayerList playerList, MatchList matchList) {
        super("remove");
        this.playerList = playerList;
        this.matchList = matchList;
    }

    @Override
    public void execute() {     // TODO: quitar switch
        String playerName = this.getArgument(0);

        if (this.matchList.contains(playerName)) {
            Message.ERASE_MATCHED_PLAYER_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            Scanner scanner = new Scanner(System.in);   // TODO: usar como argumento
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
}
