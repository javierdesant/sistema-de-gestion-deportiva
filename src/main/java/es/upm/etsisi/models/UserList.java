package es.upm.etsisi.models;

import java.util.Iterator;

public class UserList extends List<User> {
    public UserList() {
        super();
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