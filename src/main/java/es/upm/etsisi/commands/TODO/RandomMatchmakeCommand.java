package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class RandomMatchmakeCommand extends Command {
    private final EntityList entityList;
    private final MatchList matchList;
    private final Scanner scanner;

    public RandomMatchmakeCommand(EntityList entityList, MatchList matchList, Scanner scanner) {
        super("random_matchmake");
        this.entityList = entityList;
        this.matchList = matchList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: create warning menu class
        if (this.matchList.isEmpty()) {
            this.matchList.randomize(this.entityList);
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
            Message.RANDOM_MATCHMAKE_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    this.matchList.clear();
                    this.matchList.randomize(this.entityList);
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