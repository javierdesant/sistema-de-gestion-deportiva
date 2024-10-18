package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public class ScoreCommand extends Command {
    public ScoreCommand(PlayerList playerList, MatchList matchList, String[] arguments) {
        super(playerList, matchList, "score", arguments);
    }

    @Override
    public void execute() {
        try {
            String playerName = this.getArgument(0);

            double score = Double.parseDouble(this.getArgument(1));
            this.getPlayerList().score(playerName, score);
            System.out.printf("La puntuación de %s ahora es %.2f.\n", playerName, score);   // TODO: añadir a Message enum
        } catch (NumberFormatException e) {
            Message.INVALID_NUMBER.writeln();
        }
    }
}