package es.upm.etsisi.service;

import java.util.Collection;
import java.util.LinkedList;

public abstract class List<T> {
    private final LinkedList<T> elements;

    public List() {
        this.elements = new LinkedList<>();
    }

    public LinkedList<T> getElements() {
        return new LinkedList<>(this.elements);
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

    public void clear() {
        this.elements.clear();
    }

    protected void addElement(T element) {
        this.elements.add(element);
    }

    protected boolean removeElement(T element) {
        return this.elements.remove(element);
    }

    public abstract void add(T element);

    public abstract void remove(T element);
}
