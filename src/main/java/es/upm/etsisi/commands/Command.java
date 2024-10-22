package es.upm.etsisi.commands;

import es.upm.etsisi.*;

public abstract class Command implements Item {
    private final PlayerList playerList;
    private final MatchList matchList;
    private final String name_;
    private String name;
    private String[] arguments;

    public Command(PlayerList playerList, MatchList matchList, String name) {
        this.playerList = playerList;
        this.matchList = matchList;
        this.name_ = name;
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

    public void validate(String input) {
        String[] split = input.split(" ");
        this.name = split[0];
        if (split.length > 1) {
            this.arguments = split[1].split(";");
        } else {
            this.arguments = new String[0];
        }
    }

    public boolean isCalled() {
        return this.name.equals(this.name_);
    }
}
