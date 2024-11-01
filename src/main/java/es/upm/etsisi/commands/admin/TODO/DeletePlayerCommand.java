package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.Player;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class DeletePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final EntityList entityList;
    private final MatchList matchList;
    private final Scanner scanner;

    public DeletePlayerCommand(EntityList entityList, MatchList matchList, Scanner scanner) {
        super("player-remove");
        this.entityList = entityList;
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
//                    this.entityList.remove(new Player(playerName));       // FIXME
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
//            this.entityList.remove(new Player(playerName));         // FIXME
            Message.PLAYER_REMOVED.writeln();
        }
    }
}
