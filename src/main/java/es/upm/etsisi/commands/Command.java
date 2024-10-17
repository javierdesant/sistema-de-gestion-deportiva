package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public abstract class Command {
    private final PlayerList playerList;
    private final MatchList matchList;
    private final String name;
    private final String[] arguments;

    public Command(PlayerList playerList, MatchList matchList, String name, String[] arguments) {
        this.playerList = playerList;
        this.matchList = matchList;
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return this.name;
    }

    protected PlayerList getPlayerList() {
        return this.playerList;
    }

    protected MatchList getMatchList() {
        return this.matchList;
    }

    protected String getArgument(int index) {     // TODO: añadir un máximo de args dependiendo del comando
        assert this.arguments.length > index : Message.INVALID_ARGUMENTS;

        return this.arguments[index];
    }

    public boolean isCalled(String name) {
        return this.name.equals(name);
    }

    public abstract void execute();
}
