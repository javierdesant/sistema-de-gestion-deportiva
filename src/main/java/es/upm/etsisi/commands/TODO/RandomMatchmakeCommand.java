package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class RandomMatchmakeCommand extends Command {
    private final ParticipantList participantList;
    private final MatchList matchList;
    private final Scanner scanner;

    public RandomMatchmakeCommand(ParticipantList participantList, MatchList matchList, Scanner scanner) {
        super("random_matchmake");
        this.participantList = participantList;
        this.matchList = matchList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {     // TODO: create warning menu class
        if (this.matchList.isEmpty()) {
            this.matchList.randomize(this.participantList);
            Message.MATCHES_RANDOMIZED.writeln();
        } else {
            Message.RANDOM_MATCHMAKE_WARNING.writeln();
            Message.CONTINUE_PROMPT.write();
            switch (scanner.nextLine().toUpperCase()) {
                case "S":
                    this.matchList.clear();
                    this.matchList.randomize(this.participantList);
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