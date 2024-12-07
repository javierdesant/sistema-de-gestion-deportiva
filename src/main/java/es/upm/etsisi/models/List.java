package es.upm.etsisi.models;

import es.upm.etsisi.service.ErrorType;

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

    public int size() {
        return this.elements.size();
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

    public ErrorType add(T element) {
        ErrorType error;

        if (!this.contains(element)) {
            boolean added = this.elements.add(element);
            assert added;
            return ErrorType.NULL;
        } else {
            error = ErrorType.DUPLICATE_ELEMENT_ERROR;
        }

        return error;
    }

    public boolean remove(T element) {
        return this.elements.remove(element);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        List<?> that = (List<?>) object;
        return this.elements.equals(that.elements);
    }
}
