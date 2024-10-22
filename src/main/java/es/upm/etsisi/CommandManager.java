package es.upm.etsisi;

import es.upm.etsisi.commands.*;

import java.util.Scanner;

public class CommandManager extends Manager {
    private final Scanner scanner;
    private final PlayerList playerList;
    private final MatchList matchList;

    CommandManager(PlayerList playerList, MatchList matchList) {
        super();
        assert playerList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.scanner = new Scanner(System.in);
        this.playerList = playerList;
        this.matchList = matchList;
    }

    @Override
    protected void addItems() {
        this.add(new CreateCommand(this.playerList, this.matchList));
        this.add(new RemoveCommand(this.playerList, this.matchList));
        this.add(new ShowCommand(this.playerList, this.matchList));
        this.add(new RankCommand(this.playerList, this.matchList));
        this.add(new ScoreCommand(this.playerList, this.matchList));
        this.add(new ShowMatchmakeCommand(this.playerList, this.matchList));
        this.add(new ClearMatchmakeCommand(this.playerList, this.matchList));
        this.add(new MatchmakeCommand(this.playerList, this.matchList));
        this.add(new RandomMatchmakeCommand(this.playerList, this.matchList, scanner));
        this.add(new ExitCommand(this.playerList, this.matchList));
        this.add(new HelpCommand(this.playerList, this.matchList));
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
