package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.auth.User;
import es.upm.etsisi.auth.UserList;
import es.upm.etsisi.commands.Command;

public class LoginCommand extends Command {
    private User user;
    private final UserList userList;

    public LoginCommand(User user, UserList userList) {
        super("login");
        this.user = user;
        this.userList = userList;
    }

    @Override
    public void execute() {
        assert this.user == null;   // TODO: add message

        String username = getArgument(0);
        String password = getArgument(1);
        User user = this.userList.getByUsername(username);

        assert user != null;
        assert user.validate(password);

        this.user = user;
    }
}
