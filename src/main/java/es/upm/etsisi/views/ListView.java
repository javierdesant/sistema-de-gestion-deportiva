package es.upm.etsisi.views;

import es.upm.etsisi.models.List;

public abstract class ListView<T> extends View<T> {
    public ListView() {
        super();
    }

    protected void writeList(List<T> list) {
        assert list != null;
        if (list.isEmpty()) {
            this.writeln("La lista esta vacia.");
        } else {
            for (T element : list.getElements()) {
                this.display(element);
            }
        }
    }

    public abstract void write(List<T> list);
}
