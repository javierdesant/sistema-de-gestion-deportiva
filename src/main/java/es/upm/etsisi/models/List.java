package es.upm.etsisi.models;

import es.upm.etsisi.service.Error;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class List<T> {
    private final LinkedList<T> elements;

    public List() {
        this.elements = new LinkedList<>();
    }

    public List(Collection<T> elements) {
        this.elements = new LinkedList<>(elements);
    }

    public List(List<T> elements) {
        this(elements.getElements());
    }

    public LinkedList<T> getElements() {
        return new LinkedList<>(this.elements);
    }

    public Iterator<T> iterator() {
        return new LinkedList<>(this.elements).iterator();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public boolean contains(T element) {
        return this.elements.contains(element);
    }

    public void clear() {
        this.elements.clear();
    }

    public Error add(T element) {
        Error error = null;

        if (!this.contains(element)) {
            this.elements.add(element);
        } else {
            error = Error.DUPLICATE_ELEMENT_ERROR;
        }

        return error;
    }

    public boolean remove(T element) {
        return this.elements.remove(element);
    }
}
