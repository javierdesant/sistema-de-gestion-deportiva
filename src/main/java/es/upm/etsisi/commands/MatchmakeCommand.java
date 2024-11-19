package es.upm.etsisi.commands;

import es.upm.etsisi.models.MatchList;
import es.upm.etsisi.models.ParticipantList;
import es.upm.etsisi.utils.Message;

public class MatchmakeCommand extends Command { // TODO: remake for 2.0.0
    private final ParticipantList participantList;
    private final MatchList matchList;

    public MatchmakeCommand(ParticipantList participantList, MatchList matchList) {
        super("matchmake", 0);      // TODO: define Tournament and Match models
        this.participantList = participantList;
        this.matchList = matchList;
    }

    @Override
    public void execute() {
        String homePlayerName = this.getArgument(0);
        String visitingPlayerName = this.getArgument(1);

//        this.matchList.add(new Match(this.entityList, new Player(homePlayerName), new Player(visitingPlayerName)));       // FIXME
        Message.PLAYERS_MATCHED.writeln(homePlayerName, visitingPlayerName);
    }
}