package es.upm.etsisi.commands.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.game.Match;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.service.DisplayService;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class ShowMatchmakeCommand extends Command {
    private final MatchList matchList;

    public ShowMatchmakeCommand(ParticipantList participantList, MatchList matchList) {
        super("show_matchmake", 0);
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        Message.MATCHMAKE_HEADER.writeln();

        if (this.matchList.isEmpty()) {
            Message.NO_MATCHES.writeln();
        } else {
            for (Match match : this.matchList.getElements()) {
                DisplayService.show(match);
            }
        }

        Message.FOOTER.writeln();
    }
}