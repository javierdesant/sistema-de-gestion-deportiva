package es.upm.etsisi;

public abstract class Command implements Item {
    private final String name_;
    private String name;
    private String[] arguments;

    public Command(String name) {
        this.name_ = name;
    }

    public String getName() {
        return this.name;
    }

    protected PlayerList getPlayerList() {
        return CommandManager.getPlayerList();
    }

    protected MatchList getMatchList() {
        return CommandManager.getMatchList();
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