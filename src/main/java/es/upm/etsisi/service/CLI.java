package es.upm.etsisi.service;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.auth.PlayerProfile;
import es.upm.etsisi.auth.User;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.commands.admin.TODO.*;
import es.upm.etsisi.commands.user.ExitCommand;
import es.upm.etsisi.commands.user.TODO.HelpCommand;
import es.upm.etsisi.commands.user.TODO.LoginCommand;
import es.upm.etsisi.commands.user.TODO.LogoutCommand;
import es.upm.etsisi.commands.user.TODO.RegisterCommand;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.CommandView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    private final SportsService service;
    private final LinkedList<Command> commands;
    private final AuthController authController;
    private final CommandView commandView;
    private final EntityList entityList;
    private final MatchList matchList;

    CLI(EntityList entityList, MatchList matchList, SportsService service) {
        assert entityList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.commands = new LinkedList<>();
        this.authController = new AuthController();
        this.commandView = new CommandView(this.commands, this.authController);
        this.entityList = entityList;
        this.matchList = matchList;
        this.service = service;
    }

    public ArrayList<Command> getCommands() {
        return new ArrayList<>(this.commands);
    }

    public void updateCommands() {
        User user = this.authController.getUser();

        this.commands.clear();

        this.addPublicCommands();
        if (user instanceof Administrator) {
            this.addAdminCommands();
        } else if (user instanceof PlayerProfile) {
            this.addPlayerCommands();
        }
    }

    private void addAdminCommands() {
        this.add(new CreatePlayerCommand(this.entityList));
        this.add(new CreateTeamCommand(this.entityList));
        this.add(new DeletePlayerCommand(this.entityList, this.matchList, new Scanner(System.in)));     // FIXME
        this.add(new DeleteTeamCommand(this.entityList));
        this.add(new AddToTeamCommand(this.entityList));
        this.add(new RemoveFromTeamCommand(this.entityList));
        // this.add(new CreateTournamentCommand());    // TODO
        // this.add(new DeleteTournamentCommand());     // TODO
        // this.add(new TournamentMatchmakingCommand() or MatchmakeCommand());    // TODO
    }

    private void addPlayerCommands() {
        // add tournament related commands...       // TODO
        // this.add(new ShowStatsCommand());        // TODO
    }

    private void addPublicCommands() {
        this.add(new RegisterCommand(this.authController));
        this.add(new LoginCommand(this.authController, this));
        this.add(new LogoutCommand(this.authController, this));
        this.add(new HelpCommand(this));
        this.add(new ExitCommand(this.service));
    }

    private void add(Command command) {
        this.commands.add(command);
    }

    protected void run() {
        if (!this.service.isOpen()) {
            this.service.open();
        }

        do {
            Command command = this.commandView.read();

            if (command != null) {
                try {
                    command.execute();
                } catch (AssertionError error) {
                    this.commandView.displayError(error);
                }
            }

        } while (this.service.isOpen());
    }
}
