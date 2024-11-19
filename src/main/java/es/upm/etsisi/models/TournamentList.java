package es.upm.etsisi.models;

import java.util.Iterator;

public class TournamentList extends List<Tournament> {
    public TournamentList() {
        super();
    }

    public Tournament getByName(String name) {
        Tournament res = null;

        Iterator<Tournament> iterator = this.getElements().iterator();
        while (iterator.hasNext() && res == null) {
            Tournament next = iterator.next();
            if (name.equals(next.getName())) {
                res = next;
            }
        }

        return res;
    }
}
