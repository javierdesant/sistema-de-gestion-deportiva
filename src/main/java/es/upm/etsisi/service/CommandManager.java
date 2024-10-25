package es.upm.etsisi.service;

import es.upm.etsisi.commands.TODO.*;
import es.upm.etsisi.commands.admin.CreatePlayerCommand;
import es.upm.etsisi.commands.admin.DeletePlayerCommand;
import es.upm.etsisi.commands.admin.MatchmakeCommand;
import es.upm.etsisi.commands.player.ShowCommand;
import es.upm.etsisi.commands.user.ExitCommand;
import es.upm.etsisi.commands.user.HelpCommand;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.player.PlayerList;
import es.upm.etsisi.utils.Message;

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
        this.add(new CreatePlayerCommand(this.playerList));
        this.add(new DeletePlayerCommand(this.playerList, this.matchList, scanner));
        this.add(new ShowCommand(this.playerList));
        this.add(new RankCommand(this.playerList));
        this.add(new ScoreCommand(this.playerList));
        this.add(new ShowMatchmakeCommand(this.playerList, this.matchList));
        this.add(new ClearMatchmakeCommand(this.matchList));
        this.add(new MatchmakeCommand(this.playerList, this.matchList));
        this.add(new RandomMatchmakeCommand(this.playerList, this.matchList, scanner));
        this.add(new ExitCommand(this));
        this.add(new HelpCommand());
    }

    @Override
    protected String read() {
        System.out.println();
        Message.COMMAND_PROMPT.write();

        return this.scanner.nextLine().trim();
    }
}
