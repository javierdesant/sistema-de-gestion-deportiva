package es.upm.etsisi.service;

public interface Item {
    boolean isCalled();

    void validate(String input);

    void execute();
}
