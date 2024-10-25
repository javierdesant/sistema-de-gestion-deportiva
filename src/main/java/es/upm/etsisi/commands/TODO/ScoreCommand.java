package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.PlayerList;
import es.upm.etsisi.utils.Message;

public class ScoreCommand extends Command {
    private final PlayerList playerList;

    public ScoreCommand(PlayerList playerList) {
        super("score");
        this.playerList = playerList;
    }

    @Override
    public void execute() {
        try {
            String playerName = this.getArgument(0);

            double score = Double.parseDouble(this.getArgument(1));
            this.playerList.score(playerName, score);
            Message.SCORE_UPDATED.writeln(playerName, score);
        } catch (NumberFormatException e) {
            Message.INVALID_NUMBER.writeln();
        }
    }
}