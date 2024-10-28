package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.commands.Command;

public class LogoutCommand extends Command {
    private User user;

    public LogoutCommand(User user) {
        super("login");
        this.user = user;
    }

    @Override
    public void execute() {
        assert this.user != null;   // TODO: add Message

        this.user = null;
    }
}
