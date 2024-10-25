package es.upm.etsisi.models;

public interface Item {
    boolean isCalled();

    void validate(String input);

    void execute();
}
