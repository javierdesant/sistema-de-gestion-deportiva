package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

public class ShowMatchmakeCommand extends Command {
    private final MatchList matchList;

    public ShowMatchmakeCommand(EntityList entityList, MatchList matchList) {
        super("show_matchmake");
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        Message.MATCHMAKE_HEADER.writeln();
        this.matchList.show();
        Message.FOOTER.writeln();
    }
}