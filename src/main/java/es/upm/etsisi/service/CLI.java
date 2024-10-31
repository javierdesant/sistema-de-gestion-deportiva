package es.upm.etsisi.service;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.commands.Command;
import es.upm.etsisi.commands.user.TODO.*;
import es.upm.etsisi.commands.user.*;
import es.upm.etsisi.models.game.MatchList;
import es.upm.etsisi.models.entities.EntityList;
import es.upm.etsisi.utils.Message;
import es.upm.etsisi.utils.Status;
import es.upm.etsisi.views.CommandView;

import java.util.LinkedList;

public class CLI {
    private Status status;
    private final LinkedList<Command> commands;
    private final AuthController authController;
    private final CommandView commandView;
    private final EntityList entityList;
    private final MatchList matchList;

    CLI(EntityList entityList, MatchList matchList) {
        assert entityList != null : Message.NULL_PLAYERLIST;
        assert matchList != null : Message.NULL_MATCHLIST;

        this.status = Status.CLOSED;
        this.commands = new LinkedList<>();
        this.authController = new AuthController();
        this.commandView = new CommandView(this.commands, this.authController.getUser());
        this.entityList = entityList;
        this.matchList = matchList;
    }

    public boolean isOpen() {
        return this.status == Status.OPEN;
    }

    public void open() {
        assert !this.isOpen();

        if (this.isClosed()) {
            this.updateCommands();
        }

        this.status = Status.OPEN;
    }

    public boolean isClosed() {
        return this.status == Status.CLOSED;
    }

    public void close() {
        assert !this.isClosed();

        this.commands.clear();
        this.status = Status.CLOSED;
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
        this.add(new HelpCommand());
        this.add(new ExitCommand(this));
    }

    private void add(Command command) {
        this.commands.add(command);
    }

    protected void run() {
        if (!this.isOpen()) {
            this.open();
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

        } while (this.isOpen());
    }
}
