package es.upm.etsisi.models;

import es.upm.etsisi.exceptions.DuplicateElementException;

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

    public void add(T element) throws DuplicateElementException {
        if (this.contains(element)) {
            throw new DuplicateElementException(element.toString());
        }
        this.elements.add(element);
    }

    public boolean remove(T element) {
        return this.elements.remove(element);
    }
}
