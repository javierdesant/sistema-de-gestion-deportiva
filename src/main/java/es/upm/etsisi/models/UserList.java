package es.upm.etsisi.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class UserList extends List<User> {
    public UserList() {
        super();
    }

    public UserList(Collection<User> users) {
        super(users);
    }

    public UserList(User... users) {
        this(Arrays.asList(users));
    }

    public User findByUsername(String username) {
        User user = null;

        Iterator<User> iterator = this.iterator();
        while (iterator.hasNext() && user == null) {
            User next = iterator.next();
            if (username.equals(next.getUsername())) {
                user = next;
            }
        }

        return user;
    }

    public Player findByPlayerName(String name) {
        Player player = null;

        Iterator<User> iterator = this.iterator();
        while (iterator.hasNext() && player == null) {
            User next = iterator.next();
            if (next.getRole() == Role.PLAYER && ((Player) next).getName().equals(name)) {
                player = (Player) next;
            }
        }

        return player;
    }
}
