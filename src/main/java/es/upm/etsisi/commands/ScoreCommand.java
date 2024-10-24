package es.upm.etsisi.commands;

import es.upm.etsisi.MatchList;
import es.upm.etsisi.Message;
import es.upm.etsisi.PlayerList;

public class ScoreCommand extends Command {
    public ScoreCommand(PlayerList playerList, MatchList matchList) {
        super(playerList, matchList, "score");
    }

    @Override
    public void execute() {
        try {
            String playerName = this.getArgument(0);

            double score = Double.parseDouble(this.getArgument(1));
            this.getPlayerList().score(playerName, score);
            Message.SCORE_UPDATED.writeln(playerName, score);
        } catch (NumberFormatException e) {
            Message.INVALID_NUMBER.writeln();
        }
    }
}