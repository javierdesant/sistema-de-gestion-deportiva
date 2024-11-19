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

    public User getByUsername(String username) {
        User user = null;

        Iterator<User> iterator = this.getElements().iterator();
        while (iterator.hasNext() && user == null) {
            User next = iterator.next();
            if (username.equals(next.getUsername())) {
                user = next;
            }
        }

        return user;
    }
}
