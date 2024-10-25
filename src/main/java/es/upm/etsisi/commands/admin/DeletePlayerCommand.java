package es.upm.etsisi.commands.admin;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.MatchList;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.PlayerList;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class DeletePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final PlayerList playerList;
    private final MatchList matchList;
    private final Scanner scanner;

    public DeletePlayerCommand(PlayerList playerList, MatchList matchList, Scanner scanner) {
        super("remove");
        this.playerList = playerList;
        this.matchList = matchList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: quitar switch
        String playerName = this.getArgument(0);

        if (this.matchList.contains(playerName)) {
            Message.ERASE_MATCHED_PLAYER_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (this.scanner.nextLine().toUpperCase()) {
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
