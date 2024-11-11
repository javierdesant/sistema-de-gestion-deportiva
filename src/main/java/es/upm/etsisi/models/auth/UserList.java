package es.upm.etsisi.models.auth;

import es.upm.etsisi.service.List;

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

    public void add(User user) {
        assert !this.contains(user) : "User already exists";    // TODO: add message, replace with an exception
        this.addElement(user);
    }

    public void remove(User user) {
        assert this.contains(user);
        this.removeElement(user);
    }
}
