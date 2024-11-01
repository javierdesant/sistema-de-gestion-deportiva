package es.upm.etsisi.service;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.commands.user.ExitCommand;
import es.upm.etsisi.commands.user.TODO.HelpCommand;
import es.upm.etsisi.commands.user.TODO.LoginCommand;
import es.upm.etsisi.commands.user.TODO.LogoutCommand;
import es.upm.etsisi.commands.user.TODO.RegisterCommand;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.views.CommandView;

import java.util.LinkedList;

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

    public void updateCommands() {
        User user = this.authController.getUser();
        String userClass = user == null ? "" : user.getClass().getSimpleName();

        this.commands.clear();

        switch (userClass) {
            case "Administrator":
                this.addAdminCommands();
            case "PlayerProfile":
                this.addPlayerCommands();
            default:
                this.addDefaultCommands();
        }
    }

    private void addAdminCommands() {
    }

    private void addPlayerCommands() {
    }

    private void addDefaultCommands() {
        this.add(new RegisterCommand(this.authController));
        this.add(new LoginCommand(this.authController, this));
        this.add(new LogoutCommand(this.authController, this));
        this.add(new HelpCommand(this.commands));
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
