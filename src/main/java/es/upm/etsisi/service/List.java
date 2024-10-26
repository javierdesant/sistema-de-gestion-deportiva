package es.upm.etsisi.service;

import java.util.Collection;
import java.util.LinkedList;

public abstract class List<T> {
    protected final LinkedList<T> elements;

    public List() {
        this.elements = new LinkedList<>();
    }

    public LinkedList<T> getElements() {
        return this.elements;
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public boolean contains(T element) {
        return this.elements.contains(element);
    }

    public boolean containsAll(Collection<T> elements) {
        return this.elements.containsAll(elements);
    }

    public abstract void add(T element);

    public abstract void remove(T element);

    public abstract void show();
}
