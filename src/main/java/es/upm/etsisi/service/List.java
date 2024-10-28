package es.upm.etsisi.service;

import es.upm.etsisi.models.game.Match;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class List<T> {
    private final LinkedList<T> elements;

    public List() {
        this.elements = new LinkedList<>();
    }

    protected LinkedList<T> getElements() {     // TODO: erase this method ?
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

    public void clear() {
        this.elements.clear();
    }

    protected Iterator<T> iterator() {      // TODO: erase this method ?
        return this.elements.iterator();
    }

    protected void addElement(T element) {
        this.elements.add(element);
    }

    protected boolean removeElement(T element) {
        return this.elements.remove(element);
    }

    public T get(int index) {
        return this.elements.get(index);
    }

    public int indexOf(T element) {
        return this.elements.indexOf(element);
    }

    public abstract void add(T element);

    public abstract void remove(T element);

    public abstract void show();
}
