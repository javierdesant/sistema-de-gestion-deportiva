package es.upm.etsisi.commands;

import es.upm.etsisi.*;

import java.util.Scanner;

public class RemoveCommand extends Command {
    public RemoveCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "remove");
    }

    @Override
    public void execute() {     // TODO: quitar switch
        String playerName = this.getArgument(0);

        if (this.getMatchList().contains(playerName)) {
            Message.ERASE_MATCHED_PLAYER_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            Scanner scanner = new Scanner(System.in);   // TODO: usar como argumento
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    this.getMatchList().remove(playerName);
                    this.getPlayerList().remove(new Player(playerName));
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
            this.getPlayerList().remove(new Player(playerName));
            Message.PLAYER_REMOVED.writeln();
        }
    }
}
