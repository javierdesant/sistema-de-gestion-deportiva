package es.upm.etsisi;

import java.util.LinkedList;

public abstract class Manager {
    private final LinkedList<Item> items;

    public Manager() {
        this.items = new LinkedList<>();
    }

    protected abstract void addItems();

    protected abstract boolean isOpen();

    protected void run() {  // TODO: implement Error class ?
        // TODO: implement Invalid command
        this.addItems();
        do {
            String input = this.read();
            for (Item item : this.items) {
                item.validate(input);
                if (item.isCalled()) {
                    try {
                        item.execute();
                    } catch (AssertionError error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                }
            }
        } while (this.isOpen());
    }

    protected abstract String read();

    protected void add(Item item) {
        this.items.add(item);
    }
}
