package es.upm.etsisi.commands.user.TODO;

import es.upm.etsisi.auth.Administrator;
import es.upm.etsisi.auth.PlayerProfile;
import es.upm.etsisi.auth.User;
import es.upm.etsisi.auth.UserList;
import es.upm.etsisi.commands.Command;

import java.util.Scanner;

public class RegisterCommand extends Command {
    UserList userList;
    Scanner scanner;

    public RegisterCommand(UserList userList, Scanner scanner) {
        super("register");
        this.userList = userList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        User user = null;
        String username = this.getArgument(0);
        String password = this.getArgument(1);

        assert this.userList.getByUsername(username) == null;

        if (username.endsWith("@alumnos.upm.es")) {
            user = this.readPlayerProfile();
        } else if (username.endsWith("@upm.es")) {
            user = new Administrator(username, password);
        }

        assert user != null;    // TODO: add message
        this.userList.add(user);
    }

    private PlayerProfile readPlayerProfile() {

        // TODO

        return null;
    }
}
