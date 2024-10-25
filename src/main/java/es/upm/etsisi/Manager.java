package es.upm.etsisi;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class Manager {
    private final LinkedList<Item> items;
    private Status status;

    public Manager() {
        this.items = new LinkedList<>();
    }

    protected abstract void addItems();

    public boolean isOpen() {
        return this.status == Status.OPEN;
    }

    public void open() {
        assert !this.isOpen();

        this.addItems();
        this.status = Status.OPEN;
    }

    public boolean isClosed() {
        return this.status == Status.CLOSED;
    }

    public void close() {
        assert !this.isClosed();

        this.items.clear();
        this.status = Status.CLOSED;
    }

    protected void run() {
        if (!this.isOpen()) {
            this.open();
        }

        do {
            this.addItems();    // TODO: revisar
            String input = this.read();
            boolean commandMatch = false;
            Iterator<Item> iterator = items.iterator();
            while (iterator.hasNext() && !commandMatch) {
                Item currentItem = iterator.next();
                currentItem.validate(input);
                if (currentItem.isCalled()) {
                    commandMatch = true;
                    try {
                        currentItem.execute();
                    } catch (AssertionError error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                }
            }
            if (!commandMatch)
                Message.INVALID_COMMAND.writeln();
        } while (this.isOpen());
    }

    protected abstract String read();

    protected void add(Item item) {
        this.items.add(item);
    }
}
