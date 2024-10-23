package es.upm.etsisi;

import es.upm.etsisi.commands.*;

import java.util.Scanner;

public class CommandManager extends Manager {
    private static PlayerList playerList;
    private static MatchList matchList;
    private final Scanner scanner;

    public CommandManager(PlayerList playerList, MatchList matchList) {
        super();
        assert playerList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        CommandManager.playerList = playerList;
        CommandManager.matchList = matchList;
        this.scanner = new Scanner(System.in);
    }

    public static PlayerList getPlayerList() {
        return playerList;
    }

    public static MatchList getMatchList() {
        return matchList;
    }

    @Override
    protected void addItems() {
        this.add(new CreateCommand());
        this.add(new RemoveCommand());
        this.add(new ShowCommand());
        this.add(new RankCommand());
        this.add(new ScoreCommand());
        this.add(new ShowMatchmakeCommand());
        this.add(new ClearMatchmakeCommand());
        this.add(new MatchmakeCommand());
        this.add(new ExitCommand());
        this.add(new HelpCommand());
    }

    @Override
    public boolean isOpen() {
        return true;    // TODO
    }

    @Override
    protected String read() {
        System.out.println();
        Message.COMMAND_PROMPT.write();

        return this.scanner.nextLine().trim();
    }
}