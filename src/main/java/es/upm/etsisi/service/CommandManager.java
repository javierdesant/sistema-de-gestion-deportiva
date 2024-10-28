package es.upm.etsisi.service;

import es.upm.etsisi.auth.User;
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
    private User user;

    CommandManager(EntityList entityList, MatchList matchList) {
        super();
        assert entityList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.scanner = new Scanner(System.in);
        this.entityList = entityList;
        this.matchList = matchList;
        this.user = null;
    }

    @Override
    protected void addItems() {
        // TODO: add commands
    }

    @Override
    protected String read() {
        System.out.println();
        Message.COMMAND_PROMPT.write();

        return this.scanner.nextLine().trim();
    }
}
