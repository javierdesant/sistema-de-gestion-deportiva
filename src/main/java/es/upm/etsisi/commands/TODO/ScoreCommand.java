package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

public class ScoreCommand extends Command {
    private final EntityList entityList;

    public ScoreCommand(EntityList entityList) {
        super("score");
        this.entityList = entityList;
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