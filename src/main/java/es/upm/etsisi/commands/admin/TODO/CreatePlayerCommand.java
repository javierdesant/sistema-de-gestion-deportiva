package es.upm.etsisi.commands.admin.TODO;

import es.upm.etsisi.commands.Command;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.utils.Message;

public class CreatePlayerCommand extends Command {  // TODO: remake for 2.0.0
    private final ParticipantList participantList;

    public CreatePlayerCommand(ParticipantList participantList) {
        super("player-create", 0);  // TODO!: pending player-playerprofile fix
        this.participantList = participantList;
    }

    @Override
    public void execute() {
        String playerName = this.getArgument(0);

        assert playerName.matches("[a-zA-Z]+") : Message.INVALID_NAME;

//        this.entityList.add(new Player(playerName));      // FIXME
        Message.PLAYER_ADDED.writeln();
    }
}
