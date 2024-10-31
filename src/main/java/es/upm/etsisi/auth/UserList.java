package es.upm.etsisi.auth;

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
            if (username.equals(iterator.next().getUsername())) {
                user = iterator.next();
            }
        }

        return user;
    }

    public void add(User user) {
        assert !this.contains(user);    // TODO: add message
        this.addElement(user);
    }

    public void remove(User user) {
        assert this.contains(user);
        this.removeElement(user);
    }

    @Override
    public void show() {
    }
}
