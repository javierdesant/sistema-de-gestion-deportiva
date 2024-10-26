package es.upm.etsisi.service;

import es.upm.etsisi.commands.TODO.*;
import es.upm.etsisi.commands.admin.TODO.CreatePlayerCommand;
import es.upm.etsisi.commands.admin.TODO.DeletePlayerCommand;
import es.upm.etsisi.commands.admin.TODO.MatchmakeCommand;
import es.upm.etsisi.commands.player.TODO.ShowCommand;
import es.upm.etsisi.commands.user.ExitCommand;
import es.upm.etsisi.commands.user.HelpCommand;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;

import java.util.Scanner;

public class CommandManager extends Manager {
    private final Scanner scanner;
    private final EntityList entityList;
    private final MatchList matchList;

    CommandManager(EntityList entityList, MatchList matchList) {
        super();
        assert entityList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.scanner = new Scanner(System.in);
        this.entityList = entityList;
        this.matchList = matchList;
    }

    @Override
    protected void addItems() {
        this.add(new CreatePlayerCommand(this.entityList));
        this.add(new DeletePlayerCommand(this.entityList, this.matchList, scanner));
        this.add(new ShowCommand(this.entityList));
        this.add(new RankCommand(this.entityList));
        this.add(new ScoreCommand(this.entityList));
        this.add(new ShowMatchmakeCommand(this.entityList, this.matchList));
        this.add(new ClearMatchmakeCommand(this.matchList));
        this.add(new MatchmakeCommand(this.entityList, this.matchList));
        this.add(new RandomMatchmakeCommand(this.entityList, this.matchList, scanner));
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
