package es.upm.etsisi;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class Manager {
    private final LinkedList<Item> items;

    public Manager() {
        this.items = new LinkedList<>();
    }

    protected abstract void addItems();

    protected abstract boolean isOpen();

    protected void run() {
        this.addItems();
        do {
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
