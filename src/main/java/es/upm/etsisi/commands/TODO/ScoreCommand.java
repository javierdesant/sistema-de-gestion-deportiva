package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

public class ScoreCommand extends Command {
    private final ParticipantList participantList;

    public ScoreCommand(ParticipantList participantList) {
        super("score");
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        try {
            String playerName = this.getArgument(0);

            double score = Double.parseDouble(this.getArgument(1));
            // this.entityList.score(playerName, score);    // FIXME
            Message.SCORE_UPDATED.writeln(playerName, score);
        } catch (NumberFormatException e) {
            Message.INVALID_NUMBER.writeln();
        }
    }
}